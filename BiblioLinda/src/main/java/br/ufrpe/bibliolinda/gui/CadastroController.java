package br.ufrpe.bibliolinda.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroController {

    @FXML
    private Button cadastrar;

    @FXML
    void onCadastrarClick(ActionEvent event) {
        String nome = NomeCadastro.getText();
        String login = LoginCadastro.getText();
        String senha = SenhaCadastro.getText();
        String tipo = TipoCadastro.getText();

        if (nome.isEmpty() || login.isEmpty() || senha.isEmpty() || tipo.isEmpty()) {
            try {
                throw new CamposVaziosException("Nenhum campo pode estar vazio");
            } catch (CamposVaziosException e) {
                ExcecaoVazio.setTextFill(Color.RED);
                ExcecaoVazio.setText(e.getMessage());
                return;
            }
        }
        int tipoInt;

        try {
            tipoInt = Integer.parseInt(tipo);
            if (tipoInt != 1 && tipoInt != 2) {
                ExcecaoVazio2.setTextFill(Color.RED);
                ExcecaoVazio2.setText("O tipo deve ser 1 (Administrador) ou 2 (Cliente)");
                return;
            }
        } catch (NumberFormatException ex) {
            ExcecaoVazio2.setTextFill(Color.RED);
            ExcecaoVazio2.setText("O tipo deve ser 1 (Administrador) ou 2 (Cliente)");
            return;
        }

    }

    @FXML
    private void limparMensagemErro() {
        ExcecaoVazio.setText("");
    }

    @FXML
    private void initialize() {
        NomeCadastro.setOnMouseClicked(event -> limparMensagemErro());
        LoginCadastro.setOnMouseClicked(event -> limparMensagemErro());
        SenhaCadastro.setOnMouseClicked(event -> limparMensagemErro());
        TipoCadastro.setOnMouseClicked(event -> limparMensagemErro());
    }

    @FXML
    private void limparMensagemErro2() {
        ExcecaoVazio2.setText("");
    }

    @FXML
    private void initialize2() {
        NomeCadastro.setOnMouseClicked(event -> limparMensagemErro2());
        LoginCadastro.setOnMouseClicked(event -> limparMensagemErro2());
        SenhaCadastro.setOnMouseClicked(event -> limparMensagemErro2());
        TipoCadastro.setOnMouseClicked(event -> limparMensagemErro2());
    }

    @FXML
    private Label ExcecaoVazio;

    @FXML
    private Label ExcecaoVazio2;

    @FXML
    private TextField LoginCadastro;

    @FXML
    private TextField NomeCadastro;

    @FXML
    private PasswordField SenhaCadastro;

    @FXML
    private TextField TipoCadastro;

    @FXML
    private Button levarTelaLogin;

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