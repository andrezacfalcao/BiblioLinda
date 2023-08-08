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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(LocalDate data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public LocalDate getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
}
