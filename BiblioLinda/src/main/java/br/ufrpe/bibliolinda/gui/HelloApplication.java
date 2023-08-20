package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Categoria;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.beans.TipoDeUsuario;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.dados.RepositorioLivro;
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

        Livro livro1 = new Livro("Dom Casmurro", Categoria.FICCAO, "Machado de Assis", 1899, 1);
        Livro livro2 = new Livro("1984", Categoria.FICCAO, "George Orwell", 1949, 1);
        Livro livro3 = new Livro("A Origem das Espécies", Categoria.FICCAO, "Charles Darwin", 1859, 1);
        Livro livro4 = new Livro("O Poder do Hábito", Categoria.FICCAO, "Charles Duhigg", 2012, 2);

        RepositorioLivro.getInstancia().adicionarLivro(livro1);
        RepositorioLivro.getInstancia().adicionarLivro(livro2);
        RepositorioLivro.getInstancia().adicionarLivro(livro3);
        RepositorioLivro.getInstancia().adicionarLivro(livro4);
        launch();


    }

}