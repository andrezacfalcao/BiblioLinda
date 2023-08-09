package br.ufrpe.bibliolinda.dados;

import br.ufrpe.bibliolinda.beans.Livro;
import java.util.List;

public class RepositorioLivro {
    private List<Livro> listaDeLivros;

    //construtor
    public RepositorioLivro(List<Livro> listaDeLivros) {
        this.listaDeLivros = listaDeLivros;
    }

    public List<Livro> listarLivros() {
        return listaDeLivros;
    }

    public void adicionarLivro(Livro livro){
        this.listaDeLivros.add(livro);
    }

    public void removerLivro(Livro livro){
        this.listaDeLivros.remove(livro);
    }
    
    public void editarLivro(Livro livro, Livro livroNovo){
        if(this.listaDeLivros.contains(livro)){
            int i = this.listaDeLivros.indexOf(livro);
            this.listaDeLivros.set(i,livroNovo);
        }
    }
}
