package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.beans.PagamentoMulta;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.exception.CamposVaziosException;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;
import br.ufrpe.bibliolinda.negocio.ControladorEmprestimo;
import br.ufrpe.bibliolinda.negocio.ControladorLivro;
import br.ufrpe.bibliolinda.negocio.ControladorPagamento;
import br.ufrpe.bibliolinda.negocio.ControladorSessao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class SolicitacaoDeEmprestimoController {

    ControladorPagamento controladorPagamento = ControladorPagamento.getInstancia();
    ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
    ControladorSessao sessao = ControladorSessao.getInstancia();

    @FXML
    private Label LivroSelecionado;
    @FXML
    private Label EmprestimoSolicitado;
    @FXML
    private Label LivroJaEmprestado; // Você já possui um livro empréstado no momento
    @FXML
    private Button VoltarTeladeBusca;
    @FXML
    private Button ConfirmarEmprestimo;
    @FXML
    private Button VoltarTelaInicial;
    @FXML
    private String livroSelecionadoNome;
    @FXML
    private Label MultasPendentes;

    @FXML
    void onVoltarTeladeBuscaClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-buscar-livros.fxml"));
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
    void onVoltarTelaInicialClick(ActionEvent event) {
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

    public void setLivroSelecionado() {
        livroSelecionadoNome = sessao.getLivroTemp().getNomeLivro();
        LivroSelecionado.setText(livroSelecionadoNome);
    }

    //VER ISSO HOJE AINDA ANTES DA AULA
    @FXML
    public void onConfirmarEmprestimoClick(ActionEvent event) {
        try {
            // Verificar se o usuário possui multas pendentes
            //List<PagamentoMulta> multasPendentes = ControladorPagamento.getInstancia().listarPagamentosPorCliente(usuarioSelecionado);

//            if (!multasPendentes.isEmpty()) {
//                try {
//                    throw new CamposVaziosException("Você possui multas pendentes no momento");
//                } catch (CamposVaziosException e) {
//                    MultasPendentes.setTextFill(Color.RED);
//                    MultasPendentes.setText(e.getMessage());
//                    return;
//                }
//            }

            // Verificar se o usuário já possui um empréstimo em andamento
            List<Emprestimo> emprestimosAtivos = controladorEmprestimo.obterEmprestimosAtivos();

            for (Emprestimo emprestimo : emprestimosAtivos) {
                if (emprestimo.getUsuario().equals(sessao.getUsuarioOnline())) {
                    LivroJaEmprestado.setTextFill(Color.RED);
                    LivroJaEmprestado.setText("Você já possui um livro empréstado no momento");
                    return;
                }
            }

            // O usuário não possui multas pendentes e não tem empréstimo em andamento
            Emprestimo novoEmprestimo = new Emprestimo(sessao.getUsuarioOnline(), sessao.getLivroTemp(), LocalDate.now());

            // Adicionar o novo empréstimo ao sistema
            controladorEmprestimo.adicionarEmprestimo(novoEmprestimo);

            EmprestimoSolicitado.setTextFill(Color.PURPLE);
            EmprestimoSolicitado.setText("Empréstimo solicitado com sucesso");
        } catch (ObjetoJaExisteException | ParametroInvalidoException e) {
            System.out.println("Erro");
        }
    }

    public void initialize() {
        setLivroSelecionado();
    }


}