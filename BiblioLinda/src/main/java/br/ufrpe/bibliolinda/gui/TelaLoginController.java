package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.TipoDeUsuario;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.SenhaIncorretaException;
import br.ufrpe.bibliolinda.negocio.ControladorSessao;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TelaLoginController {

    ControladorUsuario controladorUsuario = ControladorUsuario.getInstancia();
    @FXML
    private Button EntrarLogin;

    @FXML
    private Button levarTelaCadastro;

    @FXML
    private Label ExcecaoUoSIncorretos;

    @FXML
    private TextField LoginLogin;

    @FXML
    private PasswordField SenhaLogin;

    ControladorSessao sessao = ControladorSessao.getInstancia();


    //@FXML
    //void onEntrarLoginClick (ActionEvent event) throws SenhaIncorretaException, ObjetoInvalidoException {
     //   String login = LoginLogin.getText();
       // String senha = SenhaLogin.getText();
        //controladorUsuario.login(login, senha);
    //}

    // Pega os dados informados nas Boxes, chama a função de login, se verdadeira, Passa pra tela Principal
    public void onEntrarLoginClick(ActionEvent event) throws IOException, SenhaIncorretaException, ObjetoInvalidoException {
        ControladorUsuario controladorUsuario = ControladorUsuario.getInstancia();

        String login = LoginLogin.getText();
        String senha = SenhaLogin.getText();

        if (controladorUsuario.login(login, senha)) {
            String tipoUsuario = sessao.getUsuarioOnline().getTipo().toString();

            if(Objects.equals(tipoUsuario, "ADMIN")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-principal-ADMIN.fxml"));
                    Parent secondScreenParent = loader.load();

                    Scene secondScreenScene = new Scene(secondScreenParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    window.setScene(secondScreenScene);
                    window.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
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
        else {
            ExcecaoUoSIncorretos.setTextFill(Color.RED);
            ExcecaoUoSIncorretos.setText("Usuário ou senha incorretos");
        }
    }

    //void levarTelaPrincipalADMIN(ActionEvent event) {
    //    try {
    //        FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-principal-ADMIN
      //      .fxml"));
    //        Parent secondScreenParent = loader.load();

  //          Scene secondScreenScene = new Scene(secondScreenParent);
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    //        window.setScene(secondScreenScene);
    //        window.show();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}

 //   void levarTelaPrincipalCLIENTE(ActionEvent event) {
  //      try {
  //          FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-principal-CLIENTE.fxml"));
//            Parent secondScreenParent = loader.load();

  //          Scene secondScreenScene = new Scene(secondScreenParent);
    //        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

      //      window.setScene(secondScreenScene);
        //    window.show();
        //} catch (IOException e) {
         //   e.printStackTrace();
      //  }
    //}

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

    @FXML
    private Button EntrarCliente;

    @FXML
    void onEntrarClienteClick(ActionEvent event) {
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
    private Button EntrarAdmin;

    @FXML
    void onEntrarAdminClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-principal-ADMIN.fxml"));
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
    private Button ListarUsuarios;

    @FXML
    void onListarUsuariosClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teste-lista-cadastro.fxml"));
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

