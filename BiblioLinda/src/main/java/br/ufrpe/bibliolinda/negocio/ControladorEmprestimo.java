package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.Cliente;
import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.dados.RepositorioEmprestimo;

import java.time.LocalDate;
import java.util.List;

public class ControladorEmprestimo {

    //Questionar se vai ser um controlador só para emprestimo e pagamento

    private static ControladorEmprestimo instancia;

    private final RepositorioEmprestimo repositorioEmprestimo;

    private ControladorEmprestimo() {
        this.repositorioEmprestimo = new RepositorioEmprestimo();
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
        repositorioEmprestimo.adicionarEmprestimo(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        repositorioEmprestimo.removerEmprestimo(emprestimo);
    }

    public void editarEmprestimo(Emprestimo emprestimo, Emprestimo novoEmprestimo) {
        repositorioEmprestimo.editarEmprestimo(emprestimo, novoEmprestimo);
    }

    public List<Emprestimo> listarEmprestimosPorCliente(Cliente cliente) {
        return repositorioEmprestimo.listarEmprestimosPorCliente(cliente);
    }

    public List<Emprestimo> listarEmprestimosPorLivro(Livro livro) {
        return repositorioEmprestimo.listarEmprestimosPorLivro(livro);
    }

    public List<Emprestimo> listarEmprestimosEntrePeriodo(LocalDate inicio, LocalDate fim) {
        return repositorioEmprestimo.listarEmprestimosEntrePeriodo(inicio, fim);
    }
}
