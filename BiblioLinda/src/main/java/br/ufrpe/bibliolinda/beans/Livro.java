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
}
