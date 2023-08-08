package br.ufrpe.bibliolinda.beans;

public class Pagamento {
    private Emprestimo emprestimo;
    private float multa;
    private boolean statusPagamento;

    public Pagamento(Emprestimo emprestimo, float multa, boolean statusPagamento) {
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

    public boolean isStatusPagamento() {
        return statusPagamento;
    }
    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }


}
