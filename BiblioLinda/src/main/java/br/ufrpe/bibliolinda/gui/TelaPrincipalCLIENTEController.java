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

public class TelaPrincipalCLIENTEController {
    @FXML
    private Button BuscarLivros;
    @FXML
    private Button EmprestimosAtivos;
    @FXML
    private Button HistoricoLeitura;
    @FXML
    private Button Pendencias;
    @FXML
    private Button voltarTelaInicioCliente;
    @FXML
    void onBuscarLivrosClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-CLIENTE-buscar-livros.fxml"));
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
    void onEmprestimosAtivosClick (ActionEvent event) {
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

    }


    @FXML
    void onvoltarTelaInicioClienteClick (ActionEvent event) {
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
