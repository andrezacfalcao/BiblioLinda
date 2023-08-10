package br.ufrpe.bibliolinda.beans;

import java.time.LocalDate;
import java.util.Locale;

public class Livro {
    private String nomeLivro;
    private Categoria categoriaLivro;
    private String nomeAutor;
    private LocalDate anoDeLancamento;
    private int numeroDeCopias;

    public Livro(String nomeLivro, Categoria categoriaLivro, String nomeAutor, LocalDate anoDeLancamento, int numeroDeCopias) {
        this.nomeLivro = nomeLivro;
        this.categoriaLivro = categoriaLivro;
        this.nomeAutor = nomeAutor;
        this.anoDeLancamento = anoDeLancamento;
        this.numeroDeCopias = numeroDeCopias;
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

    public LocalDate getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(LocalDate anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }
    public int getNumeroDeCopias(){
        return numeroDeCopias;
    }
    public void setNumeroDeCopias(int numeroDeCopias){
        this.numeroDeCopias = numeroDeCopias;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}
