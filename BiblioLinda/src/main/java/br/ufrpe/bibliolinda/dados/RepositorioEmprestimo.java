package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEmprestimo {

    private static RepositorioEmprestimo instancia;

    private static List<Emprestimo> listaDeEmprestimos;

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

    public void adicionarEmprestimo(Emprestimo emprestimo) throws ObjetoJaExisteException {
        if (!listaDeEmprestimos.contains(emprestimo)) {
            this.listaDeEmprestimos.add(emprestimo);
        } else {
            throw new ObjetoJaExisteException(emprestimo);
        }
    }

    public void removerEmprestimo(Emprestimo emprestimo) throws ObjetoInvalidoException {
        if(listaDeEmprestimos.contains(emprestimo)){
            this.listaDeEmprestimos.remove(emprestimo);
        } else {
            throw new ObjetoInvalidoException(emprestimo);
        }
    }

    public void editarEmprestimo(Emprestimo emprestimo, Emprestimo novoEmprestimo) throws ObjetoInvalidoException {
        if(this.listaDeEmprestimos.contains(emprestimo)) {
            int i = this.listaDeEmprestimos.indexOf(emprestimo);
            this.listaDeEmprestimos.set(i,novoEmprestimo);
        } else {
            throw new ObjetoInvalidoException(emprestimo);
        }
    }

    @Override
    public String toString() {
        return "RepositorioEmprestimo{" +
                "listaDeEmprestimos=" + listaDeEmprestimos +
                '}';
    }
}
