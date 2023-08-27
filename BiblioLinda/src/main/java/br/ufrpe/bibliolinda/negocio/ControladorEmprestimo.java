package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.beans.PagamentoMulta;
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

    private static ControladorEmprestimo instancia;
    private final RepositorioEmprestimo repositorioEmprestimo;
    private final RepositorioLivro repositorioLivro;
    private final ControladorPagamento controladorPagamento;

    private ControladorEmprestimo() {
        repositorioEmprestimo = RepositorioEmprestimo.getInstancia();
        repositorioLivro = RepositorioLivro.getInstancia();
        controladorPagamento = ControladorPagamento.getInstancia();
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
        //checando se o emprestimo existe e se ele não é nulo
        if(!repositorioEmprestimo.listarEmprestimos().contains(emprestimo) && emprestimo != null){
            //checa se o usuário, livro e a data não são nulos
            if(emprestimo.getUsuario() != null && emprestimo.getLivro() != null && emprestimo.getDataEmprestimo() != null){
                // if(!emprestimo1.getUsuario().equals(emprestimo.getUsuario())){
                //checa se o livro está disponível
                if(checarLivroDisponivel(emprestimo.getLivro())){
                    repositorioEmprestimo.adicionarEmprestimo(emprestimo);
                }
            }
            else{
                throw new ParametroInvalidoException(emprestimo);
            }
        }
    }

    public void removerEmprestimo(Emprestimo emprestimo) throws ObjetoInvalidoException {
        //verifica se existe o emprestimo forneido, na lista de emprestimos e se ele não é nulo
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

        for(Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos()){
            if(emprestimo.getEmprestimoAtivoBoo()) {
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


    //pode remover???????????
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

    public boolean devolverLivro(Emprestimo emprestimo) throws ObjetoInvalidoException, ParametroInvalidoException {
        if (emprestimo != null && emprestimo.getEmprestimoAtivoBoo()) {
            for (PagamentoMulta p : controladorPagamento.listarPagamentos()) {
                if (p.getMulta() == 0) {
                    p.setStatusPagamento(true);
                    // Não está desativando o emprestimo
                    p.getEmprestimo().setEmprestimoAtivoBoo(false);
                    return true;
                } else {
                    // Chama excessão de multa (clicar no botão pra pagar)
                }
            }
        } else
            throw new ObjetoInvalidoException(emprestimo);
        return false;
    }
}
