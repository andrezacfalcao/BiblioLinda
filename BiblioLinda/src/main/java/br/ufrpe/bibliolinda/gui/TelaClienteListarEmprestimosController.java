package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;
import br.ufrpe.bibliolinda.negocio.ControladorEmprestimo;
import br.ufrpe.bibliolinda.negocio.ControladorLivro;
import br.ufrpe.bibliolinda.negocio.ControladorPagamento;
import br.ufrpe.bibliolinda.negocio.ControladorSessao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TelaClienteListarEmprestimosController {

    @FXML
    private TableView<Emprestimo> tableView;
    @FXML
    private TableColumn<Emprestimo, String> usuarioColumn;
    @FXML
    private TableColumn<Emprestimo, String> livroColumn;
    @FXML
    private Button voltarTelaInicioCliente;
    @FXML
    private TableColumn<Emprestimo, Float> multaColumn;
    @FXML
    private Button devolverLivro;
    ControladorPagamento controladorPagamento = ControladorPagamento.getInstancia();
    ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();

    @FXML
    private final ObservableList<Emprestimo> items = FXCollections.observableArrayList();

    

    public void atualizarDados() {
        try {
            initialize();
        } catch (ParametroInvalidoException e) {
            e.printStackTrace();
        }
    }

    public void initialize() throws ParametroInvalidoException {
        ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
        ControladorSessao controladorSessao = ControladorSessao.getInstancia();
        ControladorPagamento controladorPagamento = ControladorPagamento.getInstancia();
        Usuario usuarioLogado = controladorSessao.getUsuarioOnline();
        if (usuarioLogado != null) {
            List<Emprestimo> emprestimosAtivosDoUsuario = controladorEmprestimo.listarEmprestimosPorCliente(usuarioLogado);

            for (Emprestimo emprestimo : emprestimosAtivosDoUsuario) {
                controladorPagamento.calcularMulta(emprestimo); // Calcula a multa para o empréstimo
                items.add(emprestimo);
            }

            usuarioColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));
            livroColumn.setCellValueFactory(new PropertyValueFactory<>("livro"));
            multaColumn.setCellValueFactory(new PropertyValueFactory<>("multa")); // Configura a coluna da multa

            tableView.setItems(items);
        }

    }

    @FXML
    void onvoltarTelaInicioClienteClick (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-principal-CLIENTE.fxml"));
            Parent secondScreenParent = loader.load();

            Scene secondScreenScene = new Scene(secondScreenParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(secondScreenScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onDevolverLivroClick(ActionEvent event) throws ParametroInvalidoException {
        Emprestimo emprestimoSelecionado = tableView.getSelectionModel().getSelectedItem();
        ControladorSessao controladorSessao = ControladorSessao.getInstancia();
        Usuario usuarioLogado = controladorSessao.getUsuarioOnline();
        List<Emprestimo> emprestimosAtivosDoUsuario = controladorEmprestimo.listarEmprestimosPorCliente(usuarioLogado);
        if (emprestimoSelecionado != null) {
            try {
                ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
                controladorEmprestimo.devolverLivro(emprestimoSelecionado);

                // Remova o empréstimo da lista observável para refletir a devolução

                emprestimosAtivosDoUsuario.remove(emprestimoSelecionado);
                items.remove(emprestimoSelecionado);


            } catch (ObjetoInvalidoException | ParametroInvalidoException e) {
                e.printStackTrace(); // Trate o erro de acordo com suas necessidades
            }
        }
    }


    //@FXML
   /* private void ondevolverLivroClicked() {
        Emprestimo emprestimoSelecionado = tableView.getSelectionModel().getSelectedItem();
        if (emprestimoSelecionado != null) {
            try {
                ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
                controladorEmprestimo.devolverLivro(emprestimoSelecionado);



                items.remove(emprestimoSelecionado);
            } catch (ObjetoInvalidoException | ParametroInvalidoException e) {
                e.printStackTrace();
            }
        }
    }*/
}
