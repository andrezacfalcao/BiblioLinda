package br.ufrpe.bibliolinda.dados;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.bibliolinda.beans.Cliente;

public class RepositorioCliente {
    private List<Cliente> listaCliente;

    public RepositorioCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public List<Cliente> getListaCliente () {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public void adicionarCliente (Cliente cliente){
        for (int i=0; i< tamanho; i++){}

    }


}
