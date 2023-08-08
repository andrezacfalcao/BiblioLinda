package br.ufrpe.bibliolinda.beans;

import java.time.LocalDate;

public class Emprestimo {
    private Cliente cliente;
    private Livro livro;
    private LocalDate data_emprestimo;
    private LocalDate data_devolucao;

    public Emprestimo(Cliente cliente, Livro livro, LocalDate data_emprestimo, LocalDate data_devolucao) {
        this.cliente = cliente;
        this.livro = livro;
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
    }
}
