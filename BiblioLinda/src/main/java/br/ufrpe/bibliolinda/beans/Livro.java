package br.ufrpe.bibliolinda.beans;

import java.time.LocalDate;
import java.util.Objects;

public class Livro {
    private String nomeLivro;
    private Categoria categoriaLivro;
    private String nomeAutor;
    private int anoDeLancamento;
    private int totalDeCopias;

    public Livro(String nomeLivro, Categoria categoriaLivro, String nomeAutor, int anoDeLancamento, int numeroTotalDeCopias) {
        this.nomeLivro = nomeLivro;
        this.categoriaLivro = categoriaLivro;
        this.nomeAutor = nomeAutor;
        this.anoDeLancamento = anoDeLancamento;
        this.totalDeCopias = numeroTotalDeCopias;
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
    public int getNumeroDeCopias(){
        return totalDeCopias;
    }
    public void setNumeroDeCopias(int numeroDeCopias){
        this.totalDeCopias = numeroDeCopias;
    }

    public String getNomeAutor() {
        return nomeAutor;
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
