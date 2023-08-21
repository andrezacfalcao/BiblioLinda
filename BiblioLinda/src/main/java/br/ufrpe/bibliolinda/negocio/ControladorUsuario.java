package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.dados.RepositorioUsuario;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;
import br.ufrpe.bibliolinda.exception.SenhaIncorretaException;

import java.util.ArrayList;
import java.util.List;

public class ControladorUsuario {
    private static ControladorUsuario instancia;

    private final RepositorioUsuario repositorioUsuario;

    private ControladorUsuario() {
        this.repositorioUsuario = new RepositorioUsuario();
    }

    public static ControladorUsuario getInstancia() {
        if (instancia == null)
            instancia = new ControladorUsuario();
        return instancia;
    }

    public boolean login(String login, String senha) throws SenhaIncorretaException,ObjetoInvalidoException {
        List<Usuario> usuarios = this.listarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login)) {
                if (usuario.getSenha().equals(senha)) {
                    ControladorSessao.getInstancia().abrirSessao(usuario);
                    return true;
                }
                else {
                    throw new SenhaIncorretaException();
                }
            }
        }
        throw new ObjetoInvalidoException(login);
    }

    public void cadastrarUsuario(Usuario u) throws ObjetoJaExisteException, ObjetoInvalidoException {

        if (!repositorioUsuario.listarUsuarios().contains(u) && u != null) {
            if(!u.getNome().isEmpty() && !u.getLogin().isEmpty() && !u.getSenha().isEmpty() && u.getTipo() != null){
                repositorioUsuario.adicionarUsuario(u);
            }
            else{
                throw new ObjetoInvalidoException();
            }
        }
    }
    public void removerUsuario(Usuario u) throws ObjetoInvalidoException {

        if(listarUsuarios().contains(u) && u != null){
            repositorioUsuario.removerUsuario(u);

        } else{
            throw new ObjetoInvalidoException();
        }
    }

    public List<Usuario> listarUsuarios() {
        return repositorioUsuario.listarUsuarios();
    }

    public List<Usuario> buscarUsuarioPorId(int id) throws ParametroInvalidoException{
        List<Usuario> resultado = new ArrayList<>();
        if(id != 0){
            for(Usuario usuario: repositorioUsuario.listarUsuarios()){
                if(usuario.getId() == id){
                    resultado.add(usuario);
                }
            }
            return resultado;
        }
        throw new ParametroInvalidoException(id);
    }


    public List<Usuario> buscarUsuarioPeloNome(String nome) throws ParametroInvalidoException{
        List<Usuario> resultado = new ArrayList<>();
        if(!nome.isEmpty()){
            for(Usuario usuario: repositorioUsuario.listarUsuarios()){
                if(usuario.getNome().equalsIgnoreCase(nome)){
                    resultado.add(usuario);
                }
            }
            return resultado;

        }else{
            throw new ParametroInvalidoException();
        }
    }



























}