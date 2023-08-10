package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.Categoria;
import br.ufrpe.bibliolinda.beans.Livro;

import java.util.ArrayList;
import java.util.List;

public class RepositorioLivro {
    private List<Livro> listaDeLivros;

    //construtor
    public RepositorioLivro() {
        this.listaDeLivros = new ArrayList<>();
    }

    public List<Livro> listarLivros() {
        return listaDeLivros;
    }

    public void adicionarLivro(Livro livro){
        this.listaDeLivros.add(livro);
    }

    public void removerLivro(Livro livro){
        this.listaDeLivros.remove(livro);
    }
    
    public void editarLivro(Livro livro, Livro livroNovo){
        if(this.listaDeLivros.contains(livro)){
            int i = this.listaDeLivros.indexOf(livro);
            this.listaDeLivros.set(i,livroNovo);
        }
    }

    public List<Livro> listarLivrosPorCategoria(Categoria categoria) {
        List<Livro> resultado = new ArrayList<>();

        for (Livro livro : listaDeLivros)
            if (livro.getCategoriaLivro().equals(categoria))
                resultado.add(livro);

        return resultado;
    }

    public List<Livro> buscarLivrosPorAutor(String autor) {
        List<Livro> lista = new ArrayList<>();

        for (Livro livro : listaDeLivros)
            if (livro.getNomeAutor().equalsIgnoreCase(autor))
                lista.add(livro);

        return lista;
    }

    public List<Livro> buscarLivrosPorTitulo(String busca) {
        List<Livro> lista = new ArrayList<>();
        for (Livro livro : listaDeLivros)
            if (livro.getNomeLivro().toLowerCase().contains(busca.toLowerCase()))
                lista.add(livro);

        return lista;
    }

    public int numeroTotalLivros() {
        int total = 0;

        for (Livro livro : listaDeLivros)
            total += livro.getNumeroDeCopias();

        return total;
    }
}
