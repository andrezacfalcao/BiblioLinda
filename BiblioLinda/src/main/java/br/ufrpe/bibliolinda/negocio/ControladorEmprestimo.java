package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.Cliente;
import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.dados.RepositorioEmprestimo;
import br.ufrpe.bibliolinda.dados.RepositorioLivro;

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

    public ControladorEmprestimo getInstancia() {
        if (instancia == null)
            instancia = new ControladorEmprestimo();
        return instancia;
    }

    public List<Emprestimo> listarEmprestimos() {
        return repositorioEmprestimo.listarEmprestimos();
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        if(!repositorioEmprestimo.listarEmprestimos().contains(emprestimo) && emprestimo != null){
            if(emprestimo.getCliente() != null && emprestimo.getLivro() != null && emprestimo.getData_emprestimo() != null && emprestimo.getData_devolucao() != null){
                repositorioEmprestimo.adicionarEmprestimo(emprestimo);
            }
            else{
                // a excessão será lançada informando que um dos atributos está inválido
            }
        }
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        if(repositorioEmprestimo.listarEmprestimos().contains(emprestimo) && emprestimo != null){
            repositorioEmprestimo.removerEmprestimo(emprestimo);
        }
        else{
            // a excessão será lançada aqui caso objeto não esteja disponível ou ele for nulo
        }
    }

    public void editarEmprestimo(Emprestimo emprestimo, Emprestimo novoEmprestimo) {
        if(repositorioEmprestimo.listarEmprestimos().contains(emprestimo) && emprestimo != null){
            if(novoEmprestimo.getCliente() != null && novoEmprestimo.getLivro() != null && novoEmprestimo.getData_emprestimo() != null && novoEmprestimo.getData_devolucao() != null ){
                repositorioEmprestimo.editarEmprestimo(emprestimo, novoEmprestimo);
            }
            else{
                // a excessão será lançada informando que um dos atributos está inválido
            }
        }
        else{
            // a excessão será lançada aqui caso objeto não esteja no repositório ou seja nulo
        }
    }

    public List<Emprestimo> listarEmprestimosPorCliente(Cliente cliente) {
        List<Emprestimo> resultado = new ArrayList<>();

        if(cliente != null){
            for (Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos())
                if (emprestimo.getCliente().equals(cliente))
                    resultado.add(emprestimo);

            return resultado;
        }
        else{
            return null; // Substitui pela execessão de ParametroInvalido
        }
    }

    public List<Emprestimo> obterEmprestimosAtivos(){
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        LocalDate hoje = LocalDate.now();

        for(Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos()){
            if(emprestimo.getData_devolucao().isAfter(hoje)){
                emprestimosAtivos.add(emprestimo);
            }
        }
        return emprestimosAtivos;
    }

    public List<Emprestimo> listarEmprestimosPorLivro(Livro livro) {
        List<Emprestimo> resultado = new ArrayList<>();

        if(livro != null){
            for (Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos()) {
                if (emprestimo.getLivro().equals(livro)) {
                    resultado.add(emprestimo);
                }
            }
            return resultado;
        }
        else{
            return null; // Substitui pela execessão de ParametroInvalido
        }
    }

    public Emprestimo obterEmprestimoIdCliente(int id) {
        if(id != 0){
            for ( Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos()){
                if (emprestimo.getCliente().getId() == id) {
                    return emprestimo;
                }
                else{
                    return null; // Substitui pela excessão de ObjetoNãoExiste
                }
            }
        }
        else {
            return null; // Substitui pela execessão de ParametroInvalido
        }
        return null; // Não sei pq, mas tava dando erro :(
    }

    public void removerEmprestimoPorId (int id){
        if(id != 0){
            for (Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos()){
                if (emprestimo.getCliente().getId() == id) {
                    repositorioEmprestimo.listarEmprestimos().remove(emprestimo);
                }
            }
        }
        else{
            // Substitui pela execessão de ParametroInvalido
        }
    }

    public List<Emprestimo> listarEmprestimosEntrePeriodo(LocalDate inicio, LocalDate fim) {
        List<Emprestimo> resultado = new ArrayList<>();
        LocalDate dataEmprestimo;

        if(inicio != null && fim != null){
            for (Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos()) {
                dataEmprestimo = emprestimo.getData_emprestimo();
                if (dataEmprestimo.isAfter(inicio) && dataEmprestimo.isBefore(fim))
                    resultado.add(emprestimo);
            }
            return resultado;
        }
        else{
            return null; // Substitui pela execessão de ParametroInvalido
        }
    }

    public boolean checarLivroDisponivel (Livro livro){
        List<Livro> livrosDisp = ControladorLivro.getInstancia().livrosDisponiveis();
        if(livro != null){
            if(livrosDisp.contains(livro)){
                return true; // Livro está disponível
            } else{
                return false;
            }
        }
        return false; // Substitui pela execessão de ParametroInvalido
    }

    public boolean checarUsuarioJaEstaComLivro(Cliente cliente, Livro livro) {
        for (Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos()) {
            if (emprestimo.getCliente().equals(cliente) && emprestimo.getLivro().equals(livro)) {
                return true;
            }
        }
        return false;
    }
}
