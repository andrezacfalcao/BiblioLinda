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

public class CadastroController {

    @FXML
    private Button cadastrar;
    @FXML
    private Button levarTelaLogin;

    @FXML
    private Label ExcecaoVazio;
    @FXML
    private Label ExcecaoVazio1;
    @FXML
    private Label ExcecaoVazio2;
    @FXML
    private Label ExcecaoVazio3;
    @FXML
    private TextField LoginCadastro;
    @FXML
    private TextField NomeCadastro;
    @FXML
    private PasswordField SenhaCadastro;
    @FXML
    ComboBox<TipoDeUsuario> TipoCadastro = new ComboBox<>();

    @FXML
    private void limparMensagemErro() {
        ExcecaoVazio1.setText("");
        ExcecaoVazio.setText("");
        ExcecaoVazio2.setText("");
        ExcecaoVazio3.setText("");
    }

    public void onCadastrarClick(ActionEvent event) throws IOException, ObjetoJaExisteException, ObjetoInvalidoException, CamposVaziosException {

        try {
            // Cadastrar novo usuario
            ControladorUsuario controladorUsuario = ControladorUsuario.getInstancia();
            String nome = NomeCadastro.getText();
            String login = LoginCadastro.getText();
            String senha = SenhaCadastro.getText();
            TipoDeUsuario tipo = TipoCadastro.getValue();

            // Verifica se os Campos da Interface estão vazios
            if (nome.isEmpty() || login.isEmpty() || senha.isEmpty() || tipo == null) {
                try {
                    throw new CamposVaziosException("Nenhum campo pode estar vazio");
                } catch (CamposVaziosException e) {
                    ExcecaoVazio.setTextFill(Color.RED);
                    ExcecaoVazio.setText(e.getMessage());
                    return;
                }
            }

            Usuario usuario = new Usuario(nome, login, senha, tipo);
            controladorUsuario.cadastrarUsuario(usuario);

            // Confirmar Cadastro
            ExcecaoVazio1.setTextFill(Color.PURPLE);
            ExcecaoVazio1.setText("Conta criada com sucesso");

        } catch (ObjetoJaExisteException e) {
            try {
                throw new ObjetoJaExisteException("Usuário já existe");
            } catch (ObjetoJaExisteException f) {
                ExcecaoVazio3.setTextFill(Color.RED);
                ExcecaoVazio3.setText(f.getMessage());
                return;
            }
        } catch (ObjetoInvalidoException e) {
            try {
                throw new CamposVaziosException("Usuário ou senha inválidos");
            } catch (CamposVaziosException f) {
                ExcecaoVazio2.setTextFill(Color.RED);
                ExcecaoVazio2.setText(f.getMessage());
                return;
            }
        }
    }

    @FXML
    private void initialize() {
        NomeCadastro.setOnMouseClicked(event -> limparMensagemErro());
        LoginCadastro.setOnMouseClicked(event -> limparMensagemErro());
        SenhaCadastro.setOnMouseClicked(event -> limparMensagemErro());
        TipoCadastro.setOnMouseClicked(event -> limparMensagemErro());
        TipoCadastro.getItems().addAll(Arrays.asList(TipoDeUsuario.ADMIN, TipoDeUsuario.CLIENTE));
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