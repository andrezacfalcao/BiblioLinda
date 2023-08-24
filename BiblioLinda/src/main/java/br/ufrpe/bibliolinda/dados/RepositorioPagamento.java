package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.PagamentoMulta;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;

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

    public void removerPendencia(PagamentoMulta pagamento) throws ObjetoInvalidoException {
        if (listaDePagamentos.contains(pagamento))
            this.listaDePagamentos.remove(pagamento);
        else
            throw new ObjetoInvalidoException(pagamento);
    }
    public void adicionarPagamento(PagamentoMulta pagamento) throws ObjetoJaExisteException {
        if (!listaDePagamentos.contains(pagamento))
            this.listaDePagamentos.add(pagamento);
        else
            throw new ObjetoJaExisteException(pagamento);
    }

    public void editarPagamento(PagamentoMulta pagamento, PagamentoMulta novoPagamento) throws ObjetoInvalidoException {
        if (this.listaDePagamentos.contains(pagamento)) {
            int i = this.listaDePagamentos.indexOf(pagamento);
            this.listaDePagamentos.set(i,novoPagamento);
        } else
            throw new ObjetoInvalidoException(pagamento);
    }
}
