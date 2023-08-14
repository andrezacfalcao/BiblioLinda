package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.PagamentoMulta;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPagamento {

    private final List<PagamentoMulta> listaDePagamentos;

    //construtor
    public RepositorioPagamento() {
        this.listaDePagamentos = new ArrayList<>();
    }

    public List<PagamentoMulta> listarPagamentos() {
        return listaDePagamentos;
    }

    public void removerPendencia(PagamentoMulta pagamento){
        if (listaDePagamentos.contains(pagamento))
            this.listaDePagamentos.remove(pagamento);
        else {
            // exceção informando que o pagamento não existe na lista
        }
    }
    public void adicionarPagamento(PagamentoMulta pagamento){
        if (!listaDePagamentos.contains(pagamento))
            this.listaDePagamentos.add(pagamento);
        else {
            // exceção informando que o pagamento já existe na lista
        }
    }

    public void editarPagamento(PagamentoMulta pagamento, PagamentoMulta novoPagamento){
        if (this.listaDePagamentos.contains(pagamento)) {
            int i = this.listaDePagamentos.indexOf(pagamento);
            this.listaDePagamentos.set(i,novoPagamento);
        } else {
            // exceção informando que o pagamento não existe na lista
        }
    }
}
