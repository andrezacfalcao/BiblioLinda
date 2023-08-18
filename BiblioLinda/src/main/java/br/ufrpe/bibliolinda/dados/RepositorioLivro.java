package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.Categoria;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;

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

    public void adicionarLivro(Livro livro) throws ObjetoJaExisteException {
        if(!this.listaDeLivros.contains(livro)){
            this.listaDeLivros.add(livro);
        }
        else{
            throw new ObjetoJaExisteException(livro);
        }
    }

    public void removerLivro(Livro livro) throws ObjetoInvalidoException{
        if(this.listaDeLivros.contains(livro)){
            listaDeLivros.remove(this.listaDeLivros.indexOf(livro));
        }
        else{
            throw new ObjetoInvalidoException(livro);
        }
    }
    
    public void editarLivro(Livro livro, Livro livroNovo) throws ObjetoInvalidoException{
        if(this.listaDeLivros.contains(livro)){
            int i = this.listaDeLivros.indexOf(livro);
            this.listaDeLivros.set(i,livroNovo);
        }
        else{
            throw new ObjetoInvalidoException(livro);
        }
    }
}
