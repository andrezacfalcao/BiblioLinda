package br.ufrpe.bibliolinda.beans;

public class Cliente extends Usuario{
    private int id;

    public Cliente(String nome, String login, String senha, int tipo, int id) {
        super(nome, login, senha, tipo);
        this.id = id;
    }
}
