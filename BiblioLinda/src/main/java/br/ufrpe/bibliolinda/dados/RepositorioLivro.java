package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.Categoria;
import br.ufrpe.bibliolinda.beans.Livro;

import java.util.ArrayList;
import java.util.List;

public class RepositorioLivro {
    private List<Livro> listaDeLivros;

    private static RepositorioLivro instancia;


    //construtor
    private RepositorioLivro() {
        this.listaDeLivros = new ArrayList<>();
    }

    public static RepositorioLivro getInstancia() {
        if(instancia == null){
            instancia = new RepositorioLivro();
        }
        return instancia;
    }

    public List<Livro> listarLivros() {
        return listaDeLivros;
    }

    public void adicionarLivro(Livro livro){
        if(!this.listaDeLivros.contains(livro)){
            this.listaDeLivros.add(livro);
        }
        else{
            // a excessão será lançada aqui caso objeto já exista no repositório
        }
    }

    public void removerLivro(Livro livro){
        if(this.listaDeLivros.contains(livro)){
            listaDeLivros.remove(this.listaDeLivros.indexOf(livro));
        }
        else{
            // a excessão será lançada aqui caso objeto não esteja no repositório
        }
    }
    
    public void editarLivro(Livro livro, Livro livroNovo){
        if(this.listaDeLivros.contains(livro)){
            int i = this.listaDeLivros.indexOf(livro);
            this.listaDeLivros.set(i,livroNovo);
        }
        else{
            // a excessão será lançada aqui caso objeto não esteja no repositório
        }
    }
}
