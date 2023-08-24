package br.ufrpe.bibliolinda.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaPrincipalADMINController {

    @FXML
    private Button GerenciarEmprestimos;
    @FXML
    private Button GerenciarLivros;
    @FXML
    private Button GerenciarUsuarios;
    @FXML
    private Button VoltarTelaInicial;

    @FXML
    void onVoltarTelaInicialClick (ActionEvent event) {
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

    @FXML
    void onGerenciarEmprestimosClick (ActionEvent event) {
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

    @FXML
    void onGerenciarLivrosClick (ActionEvent event) {
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

    @FXML
    void onGerenciarUsuariosClick (ActionEvent event) {
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
