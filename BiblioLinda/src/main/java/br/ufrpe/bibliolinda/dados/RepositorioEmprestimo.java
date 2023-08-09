package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;

import java.util.List;

public class RepositorioEmprestimo {

    private List<Emprestimo> listaDeEmprestimos;

    //construtor
    public RepositorioEmprestimo(List<Emprestimo> listaDeEmprestimos) {
        this.listaDeEmprestimos = listaDeEmprestimos;
    }

    public List<Emprestimo> listarLivros() {
        return listaDeEmprestimos;
    }

    public void adicionarLivro(Emprestimo emprestimo){
        this.listaDeEmprestimos.add(emprestimo);
    }

    public void removerLivro(Emprestimo emprestimo){
        this.listaDeEmprestimos.remove(emprestimo);
    }

    public void editarLivro(Emprestimo emprestimo, Emprestimo novoEmprestimo){
        if(this.listaDeEmprestimos.contains(emprestimo)){
            int i = this.listaDeEmprestimos.indexOf(emprestimo);
            this.listaDeEmprestimos.set(i,novoEmprestimo);
        }
    }
}
