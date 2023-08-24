package br.ufrpe.bibliolinda.beans;

import java.util.Objects;

public class Livro {
    private String nomeLivro;
    private Categoria categoriaLivro;
    private String nomeAutor;
    private int anoDeLancamento;
    private int totalDeCopias;

    public Livro(String nomeLivro, Categoria categoriaLivro, String nomeAutor, int anoDeLancamento, int totalDeCopias) {
        this.nomeLivro = nomeLivro;
        this.categoriaLivro = categoriaLivro;
        this.nomeAutor = nomeAutor;
        this.anoDeLancamento = anoDeLancamento;
        this.totalDeCopias = totalDeCopias;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }
    public void setNomeLivro (String nomeLivro){
        this.nomeLivro = nomeLivro;
    }
    public Categoria getCategoriaLivro(){
        return categoriaLivro;
    }

    public void setCategoriaLivro(Categoria categoriaLivro) {
        this.categoriaLivro = categoriaLivro;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }
    public int getTotalDeCopias(){
        return totalDeCopias;
    }
    public void setTotalDeCopias(int numeroDeCopias){
        this.totalDeCopias = numeroDeCopias;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    @Override
    public String toString() {
        return nomeLivro;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Livro && ((Livro) o).getNomeLivro().equals(this.nomeLivro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeLivro);
    }
}
