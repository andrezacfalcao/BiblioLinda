package dados;

import br.ufrpe.bibliolinda.beans.Cliente;

import java.util.ArrayList;
import java.util.ListIterator;

public class RepositorioUsuario {
    private List<Usuario> listaUsuario;

    public RepositorioUsuario(List<Usuario> listaUsuario){
        this.listaUsuario = listaUsuario;
    }


    public void adicionarUsuario(Usuario u) {
        this.listaUsuario.add(u);
    }

    public void removerUsuario(Usuario u) {
        this.listaUsuario.remove(u);
    }

    public List<Usuario> listarUsuarios(){}




}
