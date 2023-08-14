package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RepositorioUsuario {
    private final List<Usuario> listaUsuario;

    public RepositorioUsuario(){
        this.listaUsuario = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario u) {
        if(!this.listarUsuarios().contains(u)){
            listaUsuario.add(u);
        }
        else{
            // a exceção será criada posteriormente
            System.out.println("O usuário já existe");
        }
    }
    public void removerUsuario(Usuario u) {
        if(listaUsuario.contains(u)){
            listaUsuario.remove(this.listaUsuario.indexOf(u));
        }
    }
    public List<Usuario> listarUsuarios(){
        return listaUsuario;
    }

}
