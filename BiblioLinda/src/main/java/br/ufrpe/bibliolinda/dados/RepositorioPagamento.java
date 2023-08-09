package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.beans.Pagamento;

import java.util.List;

public class RepositorioPagamento {

        private List<Pagamento> listaDePagamentos;

    //construtor
    public RepositorioPagamento(List<Pagamento> listaDePagamentos) {
        this.listaDePagamentos = listaDePagamentos;
    }

    public List<Pagamento> listarPagamentos() {
        return listaDePagamentos;
    }

    public void adicionarPagamento(Pagamento pagamento){
        this.listaDePagamentos.add(pagamento);
    }

    public void removerPagamento(Pagamento pagamento){
        this.listaDePagamentos.remove(pagamento);
    }

    public void editarPagamento(Pagamento pagamento, Pagamento novoPagamento){
        if(this.listaDePagamentos.contains(pagamento)){
            int i = this.listaDePagamentos.indexOf(pagamento);
            this.listaDePagamentos.set(i,novoPagamento);
        }
    }
}
