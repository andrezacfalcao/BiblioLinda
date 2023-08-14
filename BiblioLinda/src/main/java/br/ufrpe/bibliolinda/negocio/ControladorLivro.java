package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.Categoria;
import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.dados.RepositorioEmprestimo;
import br.ufrpe.bibliolinda.dados.RepositorioLivro;

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

    public void adicionarLivro(Livro livro) {
        if(!repositorioLivro.listarLivros().contains(livro) && livro != null){
            if(!livro.getNomeLivro().isEmpty() && livro.getCategoriaLivro() != null && !livro.getNomeAutor().isEmpty() &&  livro.getAnoDeLancamento() != 0 && livro.getNumeroDeCopias() != 0){
                repositorioLivro.adicionarLivro(livro);
            }
            else{
                // a excessão será lançada informando que um dos atributos está inválido
            }
        }

    }

    public void removerLivro(Livro livro) {
        if(livrosDisponiveis().contains(livro) && livro != null){
            repositorioLivro.removerLivro(livro);
        }
        else{
            // a excessão será lançada aqui caso objeto não esteja disponível ou ele for nulo
        }
    }

    public void editarLivro(Livro livro, Livro novoLivro) {
        if(livrosDisponiveis().contains(livro) && novoLivro != null){
            if(!novoLivro.getNomeLivro().isEmpty() && novoLivro.getCategoriaLivro() != null && !novoLivro.getNomeAutor().isEmpty() &&  novoLivro.getAnoDeLancamento() != 0 && novoLivro.getNumeroDeCopias() != 0){
                repositorioLivro.editarLivro(livro, novoLivro);
            }
            else{
                // a excessão será lançada informando que um dos atributos está inválido
            }
        }
        else{
            // a excessão será lançada aqui caso objeto não esteja no repositório ou seja nulo
        }
    }

    public List<Livro> listarLivrosPorCategoria(Categoria categoria) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro livro : repositorioLivro.listarLivros())
            if (livro.getCategoriaLivro().equals(categoria))
                resultado.add(livro);

        return resultado;
    }

    public List<Livro> buscarLivrosPorAutor(String autor) {
        List<Livro> lista = new ArrayList<>();

        for (Livro livro : repositorioLivro.listarLivros())
            if (livro.getNomeAutor().equalsIgnoreCase(autor))
                lista.add(livro);

        return lista;
    }

    public List<Livro> buscarLivrosPorTitulo(String busca) {
        List<Livro> lista = new ArrayList<>();
        for (Livro livro : repositorioLivro.listarLivros())
            if (livro.getNomeLivro().toLowerCase().contains(busca.toLowerCase()))
                lista.add(livro);

        return lista;
    }

    public int numeroTotalDeLivros() {
        int total = 0;

        for (Livro livro : repositorioLivro.listarLivros())
            total += livro.getNumeroDeCopias();

        return total;
    }

    public List<Livro> livrosDisponiveis() {
        List<Livro> livrosDisponiveis = new ArrayList<>();

        for (Livro livro : listarLivros()) {
            int livrosEmprestados = contarCopiasEmprestadas(livro);

            if (livro.getNumeroDeCopias() > livrosEmprestados)
                livrosDisponiveis.add(livro);
        }

        return livrosDisponiveis;
    }

    private int contarCopiasEmprestadas(Livro livro) {
        int copiasEmprestadas = 0;

        for (Emprestimo emprestimo : RepositorioEmprestimo.getInstancia().listarEmprestimos())
            if (emprestimo.getLivro().equals(livro) && emprestimo.getData_devolucao().isAfter(LocalDate.now()))
                copiasEmprestadas++;

        return copiasEmprestadas;
    }


    public static void main(String[] args) {

        // Teste do método para listar livros disponíveis

        Livro livro1 = new Livro("Dom Casmurro", Categoria.FICCAO, "Machado de Assis", 1899, 1);
        Livro livro2 = new Livro("1984", Categoria.FICCAO, "George Orwell", 1949, 1);
        Livro livro3 = new Livro("A Origem das Espécies", Categoria.FICCAO, "Charles Darwin", 1859, 1);
        Livro livro4 = new Livro("O Poder do Hábito", Categoria.FICCAO, "Charles Duhigg", 2012, 1);

        RepositorioLivro.getInstancia().adicionarLivro(livro1);
        RepositorioLivro.getInstancia().adicionarLivro(livro2);
        RepositorioLivro.getInstancia().adicionarLivro(livro3);
        RepositorioLivro.getInstancia().adicionarLivro(livro4);

        Emprestimo emprestimo1 = new Emprestimo(null, livro1, null, LocalDate.of(2023, 12, 12));
        Emprestimo emprestimo2 = new Emprestimo(null, livro3, null, LocalDate.of(2023, 12, 12));

        RepositorioEmprestimo.getInstancia().adicionarEmprestimo(emprestimo1);
        RepositorioEmprestimo.getInstancia().adicionarEmprestimo(emprestimo2);

        ControladorLivro controlador = ControladorLivro.getInstancia();

        // Deve imprimir apenas o livro2 e o livro4
        for (Livro livro : controlador.livrosDisponiveis())
            System.out.println(livro.getNomeLivro());
    }
}