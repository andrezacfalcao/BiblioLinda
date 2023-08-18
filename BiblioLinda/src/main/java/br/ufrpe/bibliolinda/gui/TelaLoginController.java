package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.SenhaIncorretaException;
import br.ufrpe.bibliolinda.negocio.ControladorUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaLoginController {

    ControladorUsuario controladorUsuario = ControladorUsuario.getInstancia();
    @FXML
    private Button EntrarLogin;

    @FXML
    private Button levarTelaCadastro;

    @FXML
    private TextField LoginLogin;

    @FXML
    private PasswordField SenhaLogin;

    @FXML
    void onEntrarLoginClick (ActionEvent event) throws SenhaIncorretaException, ObjetoInvalidoException {
        String login = LoginLogin.getText();
        String senha = SenhaLogin.getText();
        controladorUsuario.login(login, senha);
    }

    @FXML
    void onLevarTelaCadastroClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastro-view.fxml"));
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