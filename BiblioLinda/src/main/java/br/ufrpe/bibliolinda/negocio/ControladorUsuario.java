package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.Cliente;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.dados.RepositorioUsuario;

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

    public void cadastrarUsuario(Usuario u) {

        if (!repositorioUsuario.listarUsuarios().contains(u) && u != null) {
            if(u.getNome().isEmpty() && u.getLogin().isEmpty() && u.getSenha().isEmpty() && u.getTipo() !=0 || u.getTipo() != 1){
                repositorioUsuario.adicionarUsuario(u);
            }   else{
                // a excessão será lançada informando que um dos atributos está inválido
            }
        }
    }
    public void removerUsuario(Usuario u) {

        if(listarUsuarios().contains(u) && u != null){
            repositorioUsuario.removerUsuario(u);

        } else{
            // a exeção será lançada aqui
            System.out.println("usuario já existe");
        }

    }

    public List<Usuario> listarUsuarios() {
        return repositorioUsuario.listarUsuarios();
    }

    public List<Usuario> buscarUsuarioPorId(int id){
        List<Usuario> resultado = new ArrayList<>();
        if(id != 0){
            for(Usuario usuario: repositorioUsuario.listarUsuarios()){
                if(usuario instanceof Cliente){
                    if(((Cliente) usuario).getId() == id){
                        resultado.add(usuario);
                    }
                }
            }
            return resultado;
        }
        return null;
    }

    //buscar pelo nome
    // no controlar emprestimo, fazer trazer os empréstimos de um período estabelecido
    // fazer exceção























}