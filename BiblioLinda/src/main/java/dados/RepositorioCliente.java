package dados;

import java.util.ArrayList;
import br.ufrpe.bibliolinda.beans.Cliente;

public class RepositorioCliente {
    public ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
    private int tamanho;
    public ArrayList<Cliente> getListaCliente () {
        return listaCliente;
    }

    public void setListaCliente(ArrayList<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }


    public void adicionarCliente (Cliente cliente){
        for (int i=0; i< tamanho; i++){}

    }


}
