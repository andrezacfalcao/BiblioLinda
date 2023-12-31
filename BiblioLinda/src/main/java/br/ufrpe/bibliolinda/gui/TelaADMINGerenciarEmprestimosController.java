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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TelaADMINGerenciarEmprestimosController {

    @FXML
    private TableView<PagamentoMulta> tableView;
    @FXML
    private TableColumn<PagamentoMulta, String> emprestimoColumn;
    @FXML
    private TableColumn<PagamentoMulta, Float> multaColumn;
    @FXML
    private TableColumn<PagamentoMulta, String> pagoColumn;
    @FXML
    private Label totalMulta;
    @FXML
    private TableColumn<PagamentoMulta, String> dataColumn;
    @FXML
    private Button voltarTelaInicioCliente;
    @FXML
    private TextField dataInicioField;
    @FXML
    private TextField dataFimField;
    @FXML
    private Button buscarMulta;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    ControladorPagamento controladorPagamento = ControladorPagamento.getInstancia();
    ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
    @FXML
    private final ObservableList<PagamentoMulta> items = FXCollections.observableArrayList();

    public void atualizarDados(ActionEvent event) {
        try {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-ADMIN-GerenciarEmprestimos.fxml"));
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

    @FXML
    void onTotalMultaClick(ActionEvent event) {
        LocalDate dataInicio = LocalDate.parse(dataInicioField.getText(), dateFormatter);
        LocalDate dataFim = LocalDate.parse(dataFimField.getText(), dateFormatter);

        try {
            List<PagamentoMulta> pagamentosNoPeriodo = controladorPagamento.listarPagamentosEntrePeriodo(dataInicio, dataFim);

            float totalMultaPeriodo = 0;
            for (PagamentoMulta pag : pagamentosNoPeriodo) {
                totalMultaPeriodo += pag.getValorMultaParaCalcularTotal();
            }

            String formattedTotalMulta = String.format("%.2f", totalMultaPeriodo); // Formata o valor com 2 casas decimais
            totalMulta.setText("R$" + formattedTotalMulta);

        } catch (ParametroInvalidoException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void initialize() throws ParametroInvalidoException, ObjetoInvalidoException {
        ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
        ControladorSessao controladorSessao = ControladorSessao.getInstancia();
        ControladorPagamento controladorPagamento = ControladorPagamento.getInstancia();
        Usuario usuarioLogado = controladorSessao.getUsuarioOnline();

        if (usuarioLogado != null) {
            List<PagamentoMulta> pagamentosUsuario = controladorPagamento.listarPagamentos();

            for (PagamentoMulta pag : pagamentosUsuario) {
                Emprestimo emprestimo = pag.getEmprestimo();
                if (emprestimo.getEmprestimoAtivoBoo() || !emprestimo.getEmprestimoAtivoBoo()) { // Verifica se o empréstimo está ativo
                    if (!pag.getStatusPagamento()) {
                        controladorPagamento.calcularMulta(pag);
                    }
                    items.add(pag);
                }
            }

            emprestimoColumn.setCellValueFactory(new PropertyValueFactory<>("emprestimo"));
            multaColumn.setCellValueFactory(new PropertyValueFactory<>("multa"));
            pagoColumn.setCellValueFactory(new PropertyValueFactory<>("statusPagamento"));
            dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataDePagamento"));

            tableView.setItems(items);
        }
    }

    @FXML
    void onvoltarTelaInicioClienteClick (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-ADMIN-principal.fxml"));
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
        if(pagamentoSelecionado.getMulta() == 0){
            pagamentoSelecionado.setStatusPagamento(true);
        }
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