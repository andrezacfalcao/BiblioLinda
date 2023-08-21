package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.dados.RepositorioEmprestimo;
import br.ufrpe.bibliolinda.dados.RepositorioLivro;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorEmprestimo {

    //Questionar se vai ser um controlador só para emprestimo e pagamento

    private static ControladorEmprestimo instancia;

    private final RepositorioEmprestimo repositorioEmprestimo;
    private final RepositorioLivro repositorioLivro;

    private ControladorEmprestimo() {
        repositorioEmprestimo = RepositorioEmprestimo.getInstancia();
        repositorioLivro = RepositorioLivro.getInstancia();
    }

    public static ControladorEmprestimo getInstancia() {
        if (instancia == null)
            instancia = new ControladorEmprestimo();
        return instancia;
    }

    public List<Emprestimo> listarEmprestimos() {
        return repositorioEmprestimo.listarEmprestimos();
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) throws ObjetoJaExisteException, ParametroInvalidoException {
        if(!repositorioEmprestimo.listarEmprestimos().contains(emprestimo) && emprestimo != null){
            if(emprestimo.getUsuario() != null && emprestimo.getLivro() != null && emprestimo.getDataEmprestimo() != null && emprestimo.getDataDevolucao() != null){
                for(Emprestimo emprestimo1 : obterEmprestimosAtivos()){
                    if(!emprestimo1.getUsuario().equals(emprestimo.getUsuario())){
                        if(checarLivroDisponivel(emprestimo1.getLivro())){
                            repositorioEmprestimo.adicionarEmprestimo(emprestimo1);
                        }
                    }
                }
            }
            else{
                throw new ParametroInvalidoException(emprestimo);
            }
        }
    }

    public void removerEmprestimo(Emprestimo emprestimo) throws ObjetoInvalidoException {
        if(repositorioEmprestimo.listarEmprestimos().contains(emprestimo) && emprestimo != null){
            repositorioEmprestimo.removerEmprestimo(emprestimo);
        }
        else{
            // a excessão será lançada aqui caso objeto não esteja disponível ou ele for nulo
            throw new IllegalArgumentException();
        }
    }

    public void editarEmprestimo(Emprestimo emprestimo, Emprestimo novoEmprestimo) throws ObjetoInvalidoException, ParametroInvalidoException {
        if(repositorioEmprestimo.listarEmprestimos().contains(emprestimo) && emprestimo != null){
            if(novoEmprestimo.getUsuario() != null && novoEmprestimo.getLivro() != null && novoEmprestimo.getDataEmprestimo() != null && novoEmprestimo.getDataDevolucao() != null ){
                repositorioEmprestimo.editarEmprestimo(emprestimo, novoEmprestimo);
            } else {
                throw new ParametroInvalidoException("O empréstimo fornecido é inválido!");
            }
        } else {
            throw new ObjetoInvalidoException();
        }
    }

    public List<Emprestimo> listarEmprestimosPorCliente(Usuario usuario) throws ParametroInvalidoException {
        List<Emprestimo> resultado = new ArrayList<>();

        if (usuario != null) {
            for (Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos())
                if (emprestimo.getUsuario().equals(usuario))
                    resultado.add(emprestimo);

            return resultado;
        } else {
            throw new ParametroInvalidoException("O usuário não pode ser nulo!");
        }
    }

    public List<Emprestimo> obterEmprestimosAtivos(){
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        LocalDate hoje = LocalDate.now();

        for(Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos()){
            if(emprestimo.getDataEmprestimo().plusDays(31).isAfter(hoje)){
                emprestimosAtivos.add(emprestimo);
            }
        }
        return emprestimosAtivos;
    }

    public List<Emprestimo> listarEmprestimosPorLivro(Livro livro) throws ParametroInvalidoException {
        List<Emprestimo> resultado = new ArrayList<>();

        if (livro != null) {
            for (Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos()) {
                if (emprestimo.getLivro().equals(livro)) {
                    resultado.add(emprestimo);
                }
            }
            return resultado;
        } else {
            throw new ParametroInvalidoException("O livro não pode ser nulo!");
        }
    }

    public Emprestimo obterEmprestimoIdCliente(int id) throws ParametroInvalidoException {
        if (id != 0) {
            for ( Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos()) {
                if (emprestimo.getUsuario().getId() == id) {
                    return emprestimo;
                }
            }
        }
        else {
            throw new ParametroInvalidoException("O ID fornecido deve ser diferente de 0!");
        }
        return null; // Não sei pq, mas tava dando erro :(
    }

    public void removerEmprestimoPorId (int id) throws ParametroInvalidoException {
        if (id != 0)
            repositorioEmprestimo.listarEmprestimos().removeIf(emprestimo -> emprestimo.getUsuario().getId() == id);
        else
            throw new ParametroInvalidoException("O ID fornecido deve ser diferente de 0!");
    }

    public List<Emprestimo> listarEmprestimosEntrePeriodo(LocalDate inicio, LocalDate fim) throws ParametroInvalidoException {
        List<Emprestimo> resultado = new ArrayList<>();
        LocalDate dataEmprestimo;

        if (inicio != null && fim != null) {
            for (Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos()) {
                dataEmprestimo = emprestimo.getDataEmprestimo();
                if (dataEmprestimo.isAfter(inicio) && dataEmprestimo.isBefore(fim))
                    resultado.add(emprestimo);
            }
            return resultado;
        } else{
            throw new ParametroInvalidoException("As datas fornecidas são inválidas!");
        }
    }

    public boolean checarLivroDisponivel (Livro livro) throws ParametroInvalidoException {
        List<Livro> livrosDisp = ControladorLivro.getInstancia().livrosDisponiveis();
        if (livro != null) {
            return livrosDisp.contains(livro); // Livro está disponível
        }
        throw new ParametroInvalidoException("O livro não pode ser nulo!");
    }

    public boolean checarUsuarioJaEstaComLivro(Usuario usuario, Livro livro) throws ParametroInvalidoException {
        if (usuario != null && livro != null) {
            for (Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos()) {
                if (emprestimo.getUsuario().equals(usuario) && emprestimo.getLivro().equals(livro)) {
                    return true;
                }
            }
        } else
            throw new ParametroInvalidoException("Os parâmetros fornecidos são inválidos!");
        return false;
    }
}
