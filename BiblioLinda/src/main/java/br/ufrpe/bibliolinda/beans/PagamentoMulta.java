package br.ufrpe.bibliolinda.beans;

import java.time.LocalDate;

public class PagamentoMulta {
    private Emprestimo emprestimo;
    private float multa;
    private boolean statusPagamento;
    private LocalDate dataDePagamento;
    private float valorMultaParaCalcularTotal;

    @Override
    public String toString() {
        return "PagamentoMulta{" +
                "emprestimo=" + emprestimo +
                ", multa=" + multa +
                ", statusPagamento=" + statusPagamento +
                ", dataDePagamento=" + dataDePagamento +
                '}';
    }

    public PagamentoMulta(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
        this.multa = 0;
        this.valorMultaParaCalcularTotal = 0;
        this.statusPagamento = false;
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

    public void setDataDePagamento(LocalDate dataDePagamento) {
        this.dataDePagamento = dataDePagamento;
    }

    public float getValorMultaParaCalcularTotal() {
        return valorMultaParaCalcularTotal;
    }

    public void setValorMultaParaCalcularTotal(float valorMultaParaCalcularTotal) {
        this.valorMultaParaCalcularTotal = valorMultaParaCalcularTotal;
    }
}
