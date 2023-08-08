package br.ufrpe.bibliolinda.beans;

public abstract class Usuario {
    private String nome;
    private String login;
    private String senha;
    private int tipo;

    public Usuario(String nome, String login, String senha, int tipo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }
}
