package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.TipoDeUsuario;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.exception.CamposVaziosException;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;
import br.ufrpe.bibliolinda.negocio.ControladorUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class TelaDeCadastroController {

    @FXML
    private Button cadastrar;
    @FXML
    private Button levarTelaLogin;
    @FXML
    private Label excecaoVazio;
    @FXML
    private Label excecaoVazio1;
    @FXML
    private Label excecaoVazio2;
    @FXML
    private Label excecaoVazio3;
    @FXML
    private TextField loginCadastro;
    @FXML
    private TextField nomeCadastro;
    @FXML
    private PasswordField senhaCadastro;
    @FXML
    ComboBox<TipoDeUsuario> tipoCadastro = new ComboBox<>();

    @FXML
    private void limparMensagemErro() {
        excecaoVazio1.setText("");
        excecaoVazio.setText("");
        excecaoVazio2.setText("");
        excecaoVazio3.setText("");
    }

    public void onCadastrarClick(ActionEvent event) throws IOException, ObjetoJaExisteException, ObjetoInvalidoException, CamposVaziosException {

        try {
            // Cadastrar novo usuario
            ControladorUsuario controladorUsuario = ControladorUsuario.getInstancia();
            String nome = nomeCadastro.getText();
            String login = loginCadastro.getText();
            String senha = senhaCadastro.getText();
            TipoDeUsuario tipo = tipoCadastro.getValue();

            // Verifica se os Campos da Interface estão vazios
            if (nome.isEmpty() || login.isEmpty() || senha.isEmpty() || tipo == null) {
                try {
                    throw new CamposVaziosException("Nenhum campo pode estar vazio");
                } catch (CamposVaziosException e) {
                    excecaoVazio.setTextFill(Color.RED);
                    excecaoVazio.setText(e.getMessage());
                    return;
                }
            }

            Usuario usuario = new Usuario(nome, login, senha, tipo);
            controladorUsuario.cadastrarUsuario(usuario);

            // Confirmar Cadastro
            excecaoVazio1.setTextFill(Color.PURPLE);
            excecaoVazio1.setText("Conta criada com sucesso");

        } catch (ObjetoJaExisteException e) {
            try {
                throw new ObjetoJaExisteException("Usuário já existe");
            } catch (ObjetoJaExisteException f) {
                excecaoVazio3.setTextFill(Color.RED);
                excecaoVazio3.setText(f.getMessage());
                return;
            }
        } catch (ObjetoInvalidoException e) {
            try {
                throw new CamposVaziosException("Usuário ou senha inválidos");
            } catch (CamposVaziosException f) {
                excecaoVazio2.setTextFill(Color.RED);
                excecaoVazio2.setText(f.getMessage());
                return;
            }
        }
    }

    @FXML
    private void initialize() {
        nomeCadastro.setOnMouseClicked(event -> limparMensagemErro());
        loginCadastro.setOnMouseClicked(event -> limparMensagemErro());
        senhaCadastro.setOnMouseClicked(event -> limparMensagemErro());
        tipoCadastro.setOnMouseClicked(event -> limparMensagemErro());
        tipoCadastro.getItems().addAll(Arrays.asList(TipoDeUsuario.ADMIN, TipoDeUsuario.CLIENTE));
    }

    @FXML
    void onLevarTelaLoginClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-de-login.fxml"));
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