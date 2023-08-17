package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.Categoria;
import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.dados.RepositorioEmprestimo;
import br.ufrpe.bibliolinda.dados.RepositorioLivro;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorLivro {

    private static ControladorLivro instancia;

    private final RepositorioLivro repositorioLivro;

    private ControladorLivro() {
        this.repositorioLivro = RepositorioLivro.getInstancia();
    }

    public static ControladorLivro getInstancia() {
        if (instancia == null)
            instancia = new ControladorLivro();
        return instancia;
    }

    public List<Livro> listarLivros() {
        return repositorioLivro.listarLivros();
    }

    public void adicionarLivro(Livro livro) throws ObjetoInvalidoException, ObjetoJaExisteException {
        if(!repositorioLivro.listarLivros().contains(livro) && livro != null){
            if(!livro.getNomeLivro().isEmpty() && livro.getCategoriaLivro() != null && !livro.getNomeAutor().isEmpty() &&  livro.getAnoDeLancamento() != 0 && livro.getNumeroDeCopias() != 0){
                repositorioLivro.adicionarLivro(livro);
            }
            else{
                throw new ObjetoInvalidoException();
            }
        }

    }

    public void removerLivro(Livro livro) throws ObjetoInvalidoException, ParametroInvalidoException {
        if(livrosDisponiveis().contains(livro) && livro != null){
            repositorioLivro.removerLivro(livro);
        }
        else{
            throw new ObjetoInvalidoException();
        }
    }

    public void editarLivro(Livro livro, Livro novoLivro) throws ObjetoInvalidoException, ObjetoJaExisteException, ParametroInvalidoException {
        if(livro != null && novoLivro != null) {
            if(livrosDisponiveis().contains(livro)) {
                if(!livro.getNomeLivro().isEmpty() && livro.getCategoriaLivro() != null &!livro.getNomeAutor().isEmpty() &&  livro.getAnoDeLancamento() != 0 && livro.getNumeroDeCopias() != 0){
                    repositorioLivro.editarLivro(livro, novoLivro);
                }
                else {
                    throw new ObjetoInvalidoException();
                }
            }
            else {
                throw new ObjetoJaExisteException(novoLivro);
            }
        }
    }

    public List<Livro> listarLivrosPorCategoria(Categoria categoria) throws ParametroInvalidoException {

        List<Livro> resultado = new ArrayList<>();
        if(categoria != null){
            for (Livro livro : repositorioLivro.listarLivros()) {
                if (livro.getCategoriaLivro().equals(categoria)){
                    resultado.add(livro);
                }
            }
            return resultado;
        } else {
            throw new ParametroInvalidoException("A categoria fornecida é inválida!");
        }
    }

    public List<Livro> buscarLivrosPorAutor(String autor) throws ParametroInvalidoException{

        List<Livro> lista = new ArrayList<>();
        if (!autor.isEmpty()) {
            for (Livro livro : repositorioLivro.listarLivros()) {
                if (livro.getNomeAutor().equalsIgnoreCase(autor)) {
                    lista.add(livro);
                }
            }
            return lista;

        } else {
            throw new ParametroInvalidoException("Nome de autor vazio!");
        }
    }

    public List<Livro> buscarLivrosPorTitulo(String busca) throws ParametroInvalidoException {
        List<Livro> lista = new ArrayList<>();

        if (!busca.isEmpty()) {
            for (Livro livro : repositorioLivro.listarLivros()) {
                if (livro.getNomeLivro().toLowerCase().contains(busca.toLowerCase())){
                    lista.add(livro);
                }
            }
            return lista;
        } else {
            throw new ParametroInvalidoException(busca);
        }
    }

    public int numeroTotalDeLivros() {
        int total = 0;

        for (Livro livro : repositorioLivro.listarLivros())
            total += livro.getNumeroDeCopias();

        return total;
    }

    public List<Livro> livrosDisponiveis() throws ParametroInvalidoException {
        List<Livro> livrosDisponiveis = new ArrayList<>();

        for (Livro livro : listarLivros()) {
            int livrosEmprestados = contarCopiasEmprestadas(livro);

            if (livro.getNumeroDeCopias() > livrosEmprestados)
                livrosDisponiveis.add(livro);
        }

        return livrosDisponiveis;
    }

    private int contarCopiasEmprestadas(Livro livro) throws ParametroInvalidoException {

        int copiasEmprestadas = 0;
        if (livro != null) {
            for (Emprestimo emprestimo : RepositorioEmprestimo.getInstancia().listarEmprestimos()) {
                if (emprestimo.getLivro().equals(livro) && emprestimo.getDataDevolucao().isAfter(LocalDate.now())) {
                    copiasEmprestadas++;
                }
            }
            return copiasEmprestadas;
        } else {
            throw new ParametroInvalidoException("O livro fornecido não pode ser nulo!");
        }

    }


    public static void main(String[] args) throws ObjetoJaExisteException {

        // Teste do método para listar livros disponíveis

        Livro livro1 = new Livro("Dom Casmurro", Categoria.FICCAO, "Machado de Assis", 1899, 1);
        Livro livro2 = new Livro("1984", Categoria.FICCAO, "George Orwell", 1949, 1);
        Livro livro3 = new Livro("A Origem das Espécies", Categoria.FICCAO, "Charles Darwin", 1859, 1);
        Livro livro4 = new Livro("O Poder do Hábito", Categoria.FICCAO, "Charles Duhigg", 2012, 1);

        RepositorioLivro.getInstancia().adicionarLivro(livro1);
        RepositorioLivro.getInstancia().adicionarLivro(livro2);
        RepositorioLivro.getInstancia().adicionarLivro(livro3);
        RepositorioLivro.getInstancia().adicionarLivro(livro4);

        Emprestimo emprestimo1 = new Emprestimo(null, livro1, null);
        Emprestimo emprestimo2 = new Emprestimo(null, livro3, null);

        RepositorioEmprestimo.getInstancia().adicionarEmprestimo(emprestimo1);
        RepositorioEmprestimo.getInstancia().adicionarEmprestimo(emprestimo2);

        ControladorLivro controlador = ControladorLivro.getInstancia();

        // Deve imprimir apenas o livro2 e o livro4
        try {
            for (Livro livro : controlador.livrosDisponiveis())
                System.out.println(livro.getNomeLivro());
        } catch (ParametroInvalidoException e) {
            e.printStackTrace();
        }

    }
}