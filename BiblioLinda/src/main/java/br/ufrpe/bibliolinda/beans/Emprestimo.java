package br.ufrpe.bibliolinda.beans;

import java.time.LocalDate;
import java.util.Objects;

public class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    private boolean emprestimoAtivoBoo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(livro, that.livro) && Objects.equals(dataEmprestimo, that.dataEmprestimo) && Objects.equals(dataDevolucao, that.dataDevolucao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, livro, dataEmprestimo, dataDevolucao);
    }

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setCliente(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setData_emprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setData_devolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
