package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.beans.PagamentoMulta;
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
    private TableView<PagamentoMulta> tableView;
    @FXML
    private TableColumn<PagamentoMulta, String> emprestimoColumn;
    @FXML
    private TableColumn<PagamentoMulta, Float> multaColumn;
    @FXML
    private TableColumn<PagamentoMulta, String> pagoColumn;
    @FXML
    private TableColumn<PagamentoMulta, String> dataColumn;
    @FXML
    private Button voltarTelaInicioCliente;
    @FXML
    private Button devolverLivro;
    @FXML
    private Button pagarMulta;
    ControladorPagamento controladorPagamento = ControladorPagamento.getInstancia();
    ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
    @FXML
    private final ObservableList<PagamentoMulta> items = FXCollections.observableArrayList();

    public void atualizarDados(ActionEvent event) {
        try {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-CLIENTE-listar-emprestimos.fxml"));
                Parent secondScreenParent = loader.load();

                Scene secondScreenScene = new Scene(secondScreenParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(secondScreenScene);
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            tableView.setItems(null);
            items.removeAll();
            initialize();
        } catch (ParametroInvalidoException | ObjetoInvalidoException e) {
            e.printStackTrace();
        }
    }

    public void initialize() throws ParametroInvalidoException, ObjetoInvalidoException {
        ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
        ControladorSessao controladorSessao = ControladorSessao.getInstancia();
        ControladorPagamento controladorPagamento = ControladorPagamento.getInstancia();
        Usuario usuarioLogado = controladorSessao.getUsuarioOnline();

        if (usuarioLogado != null) {
            List<PagamentoMulta> pagamentosUsuario = controladorPagamento.listarPagamentosPorCliente(usuarioLogado);

            for (PagamentoMulta pag : pagamentosUsuario) {
                if(!pag.getStatusPagamento()){
                    controladorPagamento.calcularMulta(pag); // Calcula a multa para o empréstimo
                }
                items.add(pag);
            }

            emprestimoColumn.setCellValueFactory(new PropertyValueFactory<>("emprestimo"));
            multaColumn.setCellValueFactory(new PropertyValueFactory<>("multa")); // Configura a coluna da multa
            pagoColumn.setCellValueFactory(new PropertyValueFactory<>("statusPagamento"));
            dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataDePagamento"));

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
    public void onDevolverLivroClick(ActionEvent event) throws ParametroInvalidoException, ObjetoInvalidoException {
        PagamentoMulta pagamentoSelecionado = tableView.getSelectionModel().getSelectedItem();
        ControladorSessao controladorSessao = ControladorSessao.getInstancia();
        ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
        Usuario usuarioLogado = controladorSessao.getUsuarioOnline();
        List<PagamentoMulta> pagamentosUsuario = controladorPagamento.listarPagamentosPorCliente(usuarioLogado);
        if (pagamentoSelecionado != null) {
            try {
                Emprestimo emprestimo = pagamentoSelecionado.getEmprestimo();

                if(controladorEmprestimo.devolverLivro(emprestimo)){
                    // Remova o empréstimo da lista observável para refletir a devolução
                    pagamentosUsuario.remove(pagamentoSelecionado);
                    items.remove(pagamentoSelecionado);
                    atualizarDados(event);
                }

            } catch (ObjetoInvalidoException | ParametroInvalidoException e) {
                e.printStackTrace(); // Trate o erro de acordo com suas necessidades
            }
        }
    }

    @FXML
    public void onPagarMultaClick(ActionEvent event) throws ObjetoInvalidoException {
        PagamentoMulta pagamentoSelecionado = tableView.getSelectionModel().getSelectedItem();

        ControladorSessao controladorSessao = ControladorSessao.getInstancia();
        ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
        Usuario usuarioLogado = controladorSessao.getUsuarioOnline();

        List<PagamentoMulta> pagamentosUsuario = controladorPagamento.listarPagamentosPorCliente(usuarioLogado);

        if (pagamentoSelecionado != null) {
            try {
                controladorPagamento.pagarMulta(pagamentoSelecionado, pagamentoSelecionado.getMulta());
                atualizarDados(event);
            } catch (ParametroInvalidoException e) {
                e.printStackTrace(); // Trate o erro de acordo com suas necessidades
            }
        }
    }
}
