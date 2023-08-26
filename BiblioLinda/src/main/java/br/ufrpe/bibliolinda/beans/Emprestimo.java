package br.ufrpe.bibliolinda.beans;

import java.time.LocalDate;
import java.util.Objects;

public class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private LocalDate dataLimite;
    private boolean emprestimoAtivoBoo;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(livro, that.livro) && Objects.equals(dataEmprestimo, that.dataEmprestimo) && Objects.equals(dataDevolucao, that.dataDevolucao) && Objects.equals(dataLimite, that.dataLimite) && Objects.equals(emprestimoAtivoBoo, that.emprestimoAtivoBoo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, livro, dataEmprestimo, dataDevolucao, dataLimite, emprestimoAtivoBoo);
    }

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataLimite = dataEmprestimo.plusDays(30);
        this.emprestimoAtivoBoo = true;
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

    public boolean getEmprestimoAtivoBoo() {
        return emprestimoAtivoBoo;
    }

    public void setEmprestimoAtivoBoo(boolean emprestimoAtivoBoo) {
        this.emprestimoAtivoBoo = emprestimoAtivoBoo;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }




    @Override
    public String toString() {
        return
                "Usuário: " + usuario +" Livro: "+ livro;
    }
}
