package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.*;
public class ControladorSessao {
    private static final ControladorSessao instancia = new ControladorSessao();
    private Usuario usuario;
    private Livro livroTemp;
    private Usuario usuarioTemp;

    private Emprestimo emprestimoTemp;
    private PagamentoMulta pagamentoTemp;

    // Pega a instância da sessão
    public static ControladorSessao getInstancia() {
        return instancia;
    }

    // Abre a sessão com o usuário informado
    public void abrirSessao(Usuario usuario) {
        this.usuario = usuario;
    }

    // Retorna o usuário que está logado no sistema
    public Usuario getUsuarioOnline() {
        return usuario;
    }

    // Desloga o usuário do sistema
    public void encerrarSessao() {
        this.usuario = null;
    }

    public Livro getLivroTemp() {
        return livroTemp;
    }

    public void setFilmeTemp(Livro livro) {
        this.livroTemp = livro;
    }

    public Usuario getUsuarioTemp() {
        return usuarioTemp;
    }

    public void setUsuarioTemp(Usuario usuario) {
        this.usuarioTemp = usuario;
    }

    public Emprestimo getEmprestimoTemp() {
        return emprestimoTemp;
    }

    public void setEmprestimoTemp(Emprestimo emprestimoTemp) {
        this.emprestimoTemp = emprestimoTemp;
    }

    public PagamentoMulta getPagamentoTemp() {
        return pagamentoTemp;
    }

    public void setPagamentoTemp(PagamentoMulta pagamentoTemp) {
        this.pagamentoTemp = pagamentoTemp;
    }
}
