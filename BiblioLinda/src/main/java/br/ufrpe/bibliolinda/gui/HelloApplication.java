package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.*;
import br.ufrpe.bibliolinda.dados.RepositorioEmprestimo;
import br.ufrpe.bibliolinda.dados.RepositorioLivro;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;
import br.ufrpe.bibliolinda.negocio.ControladorEmprestimo;
import br.ufrpe.bibliolinda.negocio.ControladorLivro;
import br.ufrpe.bibliolinda.negocio.ControladorPagamento;
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
        ControladorPagamento controladorPagamento = ControladorPagamento.getInstancia();

        Usuario admin = new Usuario("admin", "a", "a", TipoDeUsuario.ADMIN);
        controladorUsuario.cadastrarUsuario(admin);

        Usuario cliente = new Usuario("cliente", "c", "c", TipoDeUsuario.CLIENTE);
        controladorUsuario.cadastrarUsuario(cliente);
        Usuario deza = new Usuario("deza", "d", "d", TipoDeUsuario.CLIENTE);
        controladorUsuario.cadastrarUsuario(deza);
        Usuario bea = new Usuario("bea", "b", "b", TipoDeUsuario.CLIENTE);
        controladorUsuario.cadastrarUsuario(bea);

        Livro livro1 = new Livro("Dom Casmurro", Categoria.FICCAO, "Machado de Assis", 1899, 1);
        Livro livro2 = new Livro("1984", Categoria.HISTORIA, "George Orwell", 1949, 1);
        Livro livro3 = new Livro("A Origem das Espécies", Categoria.CIENCIA, "Charles Darwin", 1859, 1);
        Livro livro4 = new Livro("O Poder do Hábito", Categoria.FILOSOFIA, "Charles Duhigg", 2012, 2);
        Livro livro5 = new Livro("Verity", Categoria.SUSPENSE, "Colleen Hoover", 2018, 1);
        Livro livro6 = new Livro("Malibu Renasce", Categoria.ROMANCE, "Taylor Jenkins", 2021, 1);
        Livro livro7 = new Livro("Fogo & Sangue", Categoria.AVENTURA, "George R. R. Martin", 2018, 3);

        controladorLivro.adicionarLivro(livro1);
        controladorLivro.adicionarLivro(livro2);
        controladorLivro.adicionarLivro(livro3);
        controladorLivro.adicionarLivro(livro4);
        controladorLivro.adicionarLivro(livro5);
        controladorLivro.adicionarLivro(livro6);
        controladorLivro.adicionarLivro(livro7);

        LocalDate dataEmprestimo = LocalDate.of(2023, 7, 6);
        LocalDate dataEmprestimo2 = LocalDate.of(2022, 2, 9);
        LocalDate dataEmprestimo3 = LocalDate.of(2022, 4, 3);
        Emprestimo emprestimo1 = new Emprestimo(cliente,livro1,dataEmprestimo);
        Emprestimo emprestimo2 = new Emprestimo(deza,livro2,dataEmprestimo2);
        Emprestimo emprestimo3 = new Emprestimo(bea,livro6,dataEmprestimo3);

        controladorEmprestimo.adicionarEmprestimo(emprestimo1);
        controladorEmprestimo.adicionarEmprestimo(emprestimo2);
        controladorEmprestimo.adicionarEmprestimo(emprestimo3);

        PagamentoMulta pag1 = new PagamentoMulta(emprestimo1);
        controladorPagamento.adicionarPagamento(pag1);

        PagamentoMulta pag2 = new PagamentoMulta(emprestimo2);
        controladorPagamento.adicionarPagamento(pag2);

        PagamentoMulta pag3 = new PagamentoMulta(emprestimo3);
        controladorPagamento.adicionarPagamento(pag3);

        launch();
    }


}