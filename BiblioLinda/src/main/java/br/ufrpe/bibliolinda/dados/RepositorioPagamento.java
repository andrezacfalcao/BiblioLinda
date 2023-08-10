package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.Cliente;
import br.ufrpe.bibliolinda.beans.Pagamento;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPagamento {

    private final List<Pagamento> listaDePagamentos;

    //construtor
    public RepositorioPagamento() {
        this.listaDePagamentos = new ArrayList<>();
    }

    public List<Pagamento> listarPagamentos() {
        return listaDePagamentos;
    }

    public void removerPendencia(Pagamento pagamento){
        this.listaDePagamentos.remove(pagamento);
    }
    public void adicionarPagamento(Pagamento pagamento){
        this.listaDePagamentos.add(pagamento);
    }

    public void editarPagamento(Pagamento pagamento, Pagamento novoPagamento){
        if(this.listaDePagamentos.contains(pagamento)){
            int i = this.listaDePagamentos.indexOf(pagamento);
            this.listaDePagamentos.set(i,novoPagamento);
        }
    }

    public List<Pagamento> listarPagamentosPorCliente(Cliente cliente) {
        List<Pagamento> resultado = new ArrayList<>();

        for (Pagamento pagamento : listaDePagamentos)
            if (pagamento.getEmprestimo().getCliente().equals(cliente))
                resultado.add(pagamento);

        return resultado;
    }

}
