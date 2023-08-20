package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.TipoDeUsuario;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;
import br.ufrpe.bibliolinda.negocio.ControladorUsuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tela-de-login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("BiblioLinda");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws ObjetoJaExisteException, ObjetoInvalidoException {
        Usuario teste = new Usuario("abc", "abc", "abc", TipoDeUsuario.ADMIN);
        ControladorUsuario controladorUsuario = ControladorUsuario.getInstancia();
        controladorUsuario.cadastrarUsuario(teste);
        launch();
    }

}