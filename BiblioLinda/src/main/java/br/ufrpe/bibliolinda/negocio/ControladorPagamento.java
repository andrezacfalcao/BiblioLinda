package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.Cliente;
import br.ufrpe.bibliolinda.beans.Pagamento;
import br.ufrpe.bibliolinda.dados.RepositorioPagamento;

import java.util.List;

public class ControladorPagamento {

    private static ControladorPagamento instancia;

    private final RepositorioPagamento repositorioPagamento;

    private ControladorPagamento() {
        this.repositorioPagamento = new RepositorioPagamento();
    }

    public ControladorPagamento getInstancia() {
        if (instancia == null)
            instancia = new ControladorPagamento();
        return instancia;
    }

    public List<Pagamento> listarPagamentos() {
        return repositorioPagamento.listarPagamentos();
    }

    public void adicionarPagamento(Pagamento pagamento) {
        repositorioPagamento.adicionarPagamento(pagamento);
    }

    public void removerPagamento(Pagamento pagamento) {
        repositorioPagamento.removerPagamento(pagamento);
    }

    public void editarPagamento(Pagamento pagamento, Pagamento novoPagamento) {
        repositorioPagamento.editarPagamento(pagamento, novoPagamento);
    }

    public List<Pagamento> listarPagamentosPorCliente(Cliente cliente) {
        return repositorioPagamento.listarPagamentosPorCliente(cliente);
    }

}
