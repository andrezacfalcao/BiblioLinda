package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.Usuario;
import java.util.List;


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

    public List<Usuario> listarUsuarios(){
        return listaUsuario;
    }

}
