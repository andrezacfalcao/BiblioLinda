package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.Cliente;
import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEmprestimo {

    private static RepositorioEmprestimo instancia;

    private final List<Emprestimo> listaDeEmprestimos;

    //construtor
    private RepositorioEmprestimo() {
        this.listaDeEmprestimos = new ArrayList<>();
    }

    public static RepositorioEmprestimo getInstancia() {
        if (instancia == null)
            instancia = new RepositorioEmprestimo();
        return instancia;
    }

    public List<Emprestimo> listarEmprestimos() {
        return listaDeEmprestimos;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo){
        this.listaDeEmprestimos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo){
        this.listaDeEmprestimos.remove(emprestimo);
    }

    public void editarEmprestimo(Emprestimo emprestimo, Emprestimo novoEmprestimo){
        if(this.listaDeEmprestimos.contains(emprestimo)){
            int i = this.listaDeEmprestimos.indexOf(emprestimo);
            this.listaDeEmprestimos.set(i,novoEmprestimo);
        }
    }

    public List<Emprestimo> listarEmprestimosPorCliente(Cliente cliente) {
        List<Emprestimo> resultado = new ArrayList<>();

        for (Emprestimo emprestimo : listaDeEmprestimos)
            if (emprestimo.getCliente().equals(cliente))
                resultado.add(emprestimo);

        return resultado;
    }

    public List<Emprestimo> listarEmprestimosPorLivro(Livro livro) {
        List<Emprestimo> resultado = new ArrayList<>();

        for (Emprestimo emprestimo : listaDeEmprestimos)
            if (emprestimo.getLivro().equals(livro))
                resultado.add(emprestimo);

        return resultado;
    }

    public List<Emprestimo> listarEmprestimosEntrePeriodo(LocalDate inicio, LocalDate fim) {
        List<Emprestimo> resultado = new ArrayList<>();
        LocalDate dataEmprestimo;

        for (Emprestimo emprestimo : listaDeEmprestimos) {
            dataEmprestimo = emprestimo.getData_emprestimo();
            if (dataEmprestimo.isAfter(inicio) && dataEmprestimo.isBefore(fim))
                resultado.add(emprestimo);
        }

        return resultado;
    }
}
