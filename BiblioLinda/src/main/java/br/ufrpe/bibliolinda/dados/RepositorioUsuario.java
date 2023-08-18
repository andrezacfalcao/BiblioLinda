package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RepositorioUsuario {
    private final List<Usuario> listaUsuario;

    public RepositorioUsuario(){
        this.listaUsuario = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario u) throws ObjetoJaExisteException {
        if(!this.listarUsuarios().contains(u)){
            listaUsuario.add(u);
        }
        else{
            throw new ObjetoJaExisteException(u);
        }
    }

    public void removerUsuario(Usuario u) throws ObjetoInvalidoException{
        if(listaUsuario.contains(u)){
            listaUsuario.remove(this.listaUsuario.indexOf(u));
        }
        else{
            throw new ObjetoInvalidoException();
        }
    }
    public List<Usuario> listarUsuarios(){
        return listaUsuario;
    }

}
