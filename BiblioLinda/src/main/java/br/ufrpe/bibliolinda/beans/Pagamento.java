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

}
