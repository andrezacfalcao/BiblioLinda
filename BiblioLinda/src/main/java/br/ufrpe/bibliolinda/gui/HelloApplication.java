package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.*;
import br.ufrpe.bibliolinda.dados.RepositorioEmprestimo;
import br.ufrpe.bibliolinda.dados.RepositorioLivro;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;
import br.ufrpe.bibliolinda.negocio.ControladorEmprestimo;
import br.ufrpe.bibliolinda.negocio.ControladorLivro;
import br.ufrpe.bibliolinda.negocio.ControladorUsuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

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

    public static void main(String[] args) throws ObjetoJaExisteException, ObjetoInvalidoException, ParametroInvalidoException {
        ControladorUsuario controladorUsuario = ControladorUsuario.getInstancia();
        ControladorLivro controladorLivro = ControladorLivro.getInstancia();
        ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();

        Usuario admin = new Usuario("admin", "a", "a", TipoDeUsuario.ADMIN);
        controladorUsuario.cadastrarUsuario(admin);

        Usuario cliente = new Usuario("cliente", "c", "c", TipoDeUsuario.CLIENTE);
        controladorUsuario.cadastrarUsuario(cliente);

        Livro livro1 = new Livro("Dom Casmurro", Categoria.FICCAO, "Machado de Assis", 1899, 1);
        Livro livro2 = new Livro("1984", Categoria.HISTORIA, "George Orwell", 1949, 1);
        Livro livro3 = new Livro("A Origem das Espécies", Categoria.CIENCIA, "Charles Darwin", 1859, 1);
        Livro livro4 = new Livro("O Poder do Hábito", Categoria.FILOSOFIA, "Charles Duhigg", 2012, 2);

        controladorLivro.adicionarLivro(livro1);
        controladorLivro.adicionarLivro(livro2);
        controladorLivro.adicionarLivro(livro3);
        controladorLivro.adicionarLivro(livro4);


        launch();
    }


}