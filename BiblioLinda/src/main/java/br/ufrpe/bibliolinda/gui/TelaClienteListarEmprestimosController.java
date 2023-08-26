package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;
import br.ufrpe.bibliolinda.negocio.ControladorEmprestimo;
import br.ufrpe.bibliolinda.negocio.ControladorLivro;
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
import javafx.stage.Stage;

import java.io.IOException;
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
    private final ObservableList<Emprestimo> items = FXCollections.observableArrayList();

    public void initialize() throws ParametroInvalidoException {
        ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
        ControladorSessao controladorSessao = ControladorSessao.getInstancia();
        Usuario usuarioLogado = controladorSessao.getUsuarioOnline();
        if (usuarioLogado != null) {
            List<Emprestimo> emprestimosAtivosDoUsuario = controladorEmprestimo.listarEmprestimosPorCliente(usuarioLogado);
            items.addAll(emprestimosAtivosDoUsuario);

            // Configura as colunas para receber os dados
            usuarioColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));
            livroColumn.setCellValueFactory(new PropertyValueFactory<>("livro"));

            // Preenche a TableView com os empréstimos ativos do usuário logado
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
}
