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

    public void removerEmprestimoPorId (int id){
        for (Emprestimo emprestimo : listaDeEmprestimos){
            if (emprestimo.getCliente().getId() == id) {
                listaDeEmprestimos.remove(emprestimo);
            }
        }

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

    public Emprestimo obterEmprestimoId(int id) {
        for ( Emprestimo emprestimo : listaDeEmprestimos){
            if (emprestimo.getCliente().getId() == id) {
                return emprestimo;
            }
        }
        return null;
    }

    public List<Emprestimo> obterEmprestimosAtivos(){ //pERA n sei se t aok
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        LocalDate hoje = LocalDate.now();

        for(Emprestimo emprestimo : listaDeEmprestimos){
            if(emprestimo.getData_devolucao().isAfter(hoje)){
                emprestimosAtivos.add(emprestimo);
            }
        }
        return emprestimosAtivos;
    }


    public List<Emprestimo> obterEmprestimosAtivosEmRelacaoAData(LocalDate data){
        List<Emprestimo> emprestimosPendentes = new ArrayList<>();
        for(Emprestimo emprestimo : listaDeEmprestimos){
            LocalDate dataDevolucao = emprestimo.getData_devolucao();
            if(dataDevolucao.isAfter(data)){
                emprestimosPendentes.add(emprestimo);
            }
        }
        return emprestimosPendentes;
    }

    public boolean checarLivroDisponivel (Livro livro){
        for( Emprestimo emprestimo : listaDeEmprestimos){
            if(emprestimo.getLivro().equals(livro) ){ // && como faço pra falar tipo "nao tem data de devolucao"?
            return false; //pois o livro vai estar emprestado
            } else{
                return true;
            }
        }
        return false; // ? tava dando erro
    }


        public boolean checarUsuarioJaEstaComLivro(Cliente cliente, Livro livro) {
            for (Emprestimo emprestimo : listaDeEmprestimos) {
                if (emprestimo.getCliente().equals(cliente) && emprestimo.getLivro().equals(livro)) {
                    return true;
                }

            }
            return false;
        }

}
