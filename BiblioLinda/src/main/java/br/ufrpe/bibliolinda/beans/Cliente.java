package br.ufrpe.bibliolinda.beans;

import java.util.Objects;

public class Cliente extends Usuario {
    private int id;

    public Cliente(String nome, String login, String senha, int tipo, int id) {
        super(nome, login, senha, tipo);
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Cliente && ((Cliente) o).getId() == this.id && ((Cliente) o).getLogin().equals(this.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}