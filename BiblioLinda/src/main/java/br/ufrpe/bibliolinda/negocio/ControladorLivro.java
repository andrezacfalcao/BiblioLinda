package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.Categoria;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.dados.RepositorioLivro;

import java.util.List;

public class ControladorLivro {

    private static ControladorLivro instancia;

    private final RepositorioLivro repositorioLivro;

    private ControladorLivro() {
        this.repositorioLivro = new RepositorioLivro();
    }

    public ControladorLivro getInstancia() {
        if (instancia == null)
            instancia = new ControladorLivro();
        return instancia;
    }

    public List<Livro> listarLivros() {
        return repositorioLivro.listarLivros();
    }

    public void adicionarLivro(Livro l) {
        repositorioLivro.adicionarLivro(l);
    }

    public void removerLivro(Livro l) {
        repositorioLivro.removerLivro(l);
    }

    public void editarLivro(Livro livro, Livro novoLivro) {
        repositorioLivro.editarLivro(livro, novoLivro);
    }

    public List<Livro> listarLivrosPorCategoria(Categoria categoria) {
        return repositorioLivro.listarLivrosPorCategoria(categoria);
    }

    public List<Livro> buscarLivrosPorAutor(String autor) {
        return repositorioLivro.buscarLivrosPorAutor(autor);
    }

    public List<Livro> buscarLivrosPorTitulo(String busca) {
        return repositorioLivro.buscarLivrosPorTitulo(busca);
    }

    public int numeroTotalDeLivros() {
        return repositorioLivro.numeroTotalLivros();
    }

}
