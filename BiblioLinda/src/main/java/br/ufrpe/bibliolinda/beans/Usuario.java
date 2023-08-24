package br.ufrpe.bibliolinda.beans;

import java.util.Objects;

public class Usuario {
    private String nome;
    private String login;
    private String senha;
    private TipoDeUsuario tipo;
    private int id;
    private static int contador = 1;

    public Usuario(String nome, String login, String senha, TipoDeUsuario tipo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
        this.id = contador++;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoDeUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeUsuario tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Usuario && ((Usuario) o).getId() == this.id && ((Usuario) o).getLogin().equals(this.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
