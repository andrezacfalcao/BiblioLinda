package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.dados.RepositorioUsuario;

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

    public void cadastrarUsuario(Usuario u) {
        repositorioUsuario.adicionarUsuario(u);
    }

    public void removerUsuario(Usuario u) {
        repositorioUsuario.removerUsuario(u);
    }

    public List<Usuario> listarUsuarios() {
        return repositorioUsuario.listarUsuarios();
    }
}
