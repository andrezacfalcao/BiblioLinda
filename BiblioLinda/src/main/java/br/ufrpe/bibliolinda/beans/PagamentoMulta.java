package br.ufrpe.bibliolinda.beans;

import java.time.LocalDate;

public class PagamentoMulta {
    private Emprestimo emprestimo;
    private float multa;
    private boolean statusPagamento;
    private LocalDate dataDePagamento;

    public PagamentoMulta(Emprestimo emprestimo, float multa, boolean statusPagamento) {
        this.emprestimo = emprestimo;
        this.multa = multa;
        this.statusPagamento = statusPagamento;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public float getMulta() {
        return multa;
    }

    public void setMulta(float multa) {
        this.multa = multa;
    }

    public boolean getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public LocalDate getDataDePagamento() {
        return dataDePagamento;
    }

    public boolean isStatusPagamento() {
        return false;
    }
}
