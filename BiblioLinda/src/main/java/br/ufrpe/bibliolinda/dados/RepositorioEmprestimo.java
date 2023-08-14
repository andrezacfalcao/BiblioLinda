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
        if(!listaDeEmprestimos.contains(emprestimo)){
            this.listaDeEmprestimos.add(emprestimo);
        }
        else{
            // Substitui pela excessão de ObjetoJáExiste
        }
    }

    public void removerEmprestimo(Emprestimo emprestimo){
        if(listaDeEmprestimos.contains(emprestimo)){
            this.listaDeEmprestimos.remove(emprestimo);
        }
        else{
            // Substitui pela excessão de ObjetoNãoExiste
        }
    }

    public void editarEmprestimo(Emprestimo emprestimo, Emprestimo novoEmprestimo){
        if(this.listaDeEmprestimos.contains(emprestimo)){
            int i = this.listaDeEmprestimos.indexOf(emprestimo);
            this.listaDeEmprestimos.set(i,novoEmprestimo);
        }
        else{
            // Substitui pela excessão de ObjetoNãoExiste
        }
    }
}
