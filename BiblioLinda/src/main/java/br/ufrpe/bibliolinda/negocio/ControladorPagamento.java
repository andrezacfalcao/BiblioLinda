package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.PagamentoMulta;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.dados.RepositorioPagamento;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorPagamento {

    private static ControladorPagamento instancia;

    private final RepositorioPagamento repositorioPagamento;

    private ControladorPagamento() {
        this.repositorioPagamento = new RepositorioPagamento();
    }

    public static ControladorPagamento getInstancia() {
        if (instancia == null)
            instancia = new ControladorPagamento();
        return instancia;
    }

    public List<PagamentoMulta> listarPagamentos() {
        return repositorioPagamento.listarPagamentos();
    }

    public void adicionarPagamento(PagamentoMulta pagamento) throws ObjetoJaExisteException, ObjetoInvalidoException {
        if (!listarPagamentos().contains(pagamento))
            if (pagamento != null && pagamento.getEmprestimo() != null
                    && pagamento.getMulta() != 0 && pagamento.getDataDePagamento() != null)
                repositorioPagamento.adicionarPagamento(pagamento);
            else
                throw new ObjetoInvalidoException();
    }

    public void removerPagamento(PagamentoMulta pagamento) throws ObjetoInvalidoException, ParametroInvalidoException {
        if (listarPagamentos().contains(pagamento))
            if (pagamento != null)
                repositorioPagamento.removerPendencia(pagamento);
            else
                throw new ParametroInvalidoException("O pagamento fornecido não pode ser nulo!");
        else
            throw new ObjetoInvalidoException(pagamento);
    }

    public void editarPagamento(PagamentoMulta pagamento, PagamentoMulta novoPagamento) throws ObjetoInvalidoException, ParametroInvalidoException {
        if (listarPagamentos().contains(pagamento)) {
            if (novoPagamento != null && novoPagamento.getEmprestimo() != null
                    && novoPagamento.getMulta() != 0 && novoPagamento.getDataDePagamento() != null) {
                repositorioPagamento.editarPagamento(pagamento, novoPagamento);
            } else {
                throw new ParametroInvalidoException("O pagamento fornecido não pode ser nulo!");
            }
        } else {
            throw new ObjetoInvalidoException();
        }
    }

    public List<PagamentoMulta> listarPagamentosPorCliente(Usuario usuario) throws ObjetoInvalidoException {
        List<PagamentoMulta> resultado = new ArrayList<>();

        if (usuario != null)
            for (PagamentoMulta pagamento : listarPagamentos())
                if (pagamento.getEmprestimo().getUsuario().equals(usuario))
                    resultado.add(pagamento);
        else
            throw new ObjetoInvalidoException();

        return resultado;
    }

    public List<PagamentoMulta> listarPagamentosEmAtraso() {
        List<PagamentoMulta> resultado = new ArrayList<>();

        for (PagamentoMulta pagamento : listarPagamentos())
            if (!pagamento.getStatusPagamento())
                resultado.add(pagamento);

        return resultado;
    }

    public List<PagamentoMulta> listarPagamentosEntrePeriodo(LocalDate inicio, LocalDate fim) throws ParametroInvalidoException {
        List<PagamentoMulta> resultado = new ArrayList<>();

        if (inicio != null && fim != null)
            for (PagamentoMulta pagamento : listarPagamentos())
                if (pagamento.getDataDePagamento().isAfter(inicio) && pagamento.getDataDePagamento().isBefore(fim))
                    resultado.add(pagamento);
        else
            throw new ParametroInvalidoException("As datas fornecidas são inválidas!");

        return resultado;
    }

    public List<PagamentoMulta> listarPagamentosAcimaDeValor(float valor) {
        List<PagamentoMulta> resultado = new ArrayList<>();

        for (PagamentoMulta pagamento : listarPagamentos())
            if (pagamento.getMulta() >= valor)
                resultado.add(pagamento);

        return resultado;
    }

    public float calcularValorTotalDePagamentos() {
        float total = 0;
        List<PagamentoMulta> pagamentos = listarPagamentos();

        if (pagamentos != null) {
            for (PagamentoMulta pagamento : pagamentos)
                total += pagamento.getMulta();
        }

        return total;
    }

    public int calcularNumeroTotalDePagamentos() {
        return listarPagamentos().size();
    }

    public String gerarRelatorioDePagamentos(LocalDate inicio, LocalDate fim) throws ParametroInvalidoException {
        List<PagamentoMulta> pagamentos = listarPagamentosEntrePeriodo(inicio, fim);

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Pagamentos\n");
        relatorio.append("Período: ").append(inicio).append(" a ").append(fim).append("\n\n");

        for (PagamentoMulta pagamento : pagamentos) {
            relatorio.append("Data: ").append(pagamento.getDataDePagamento()).append("\n");
            relatorio.append("Cliente: ").append(pagamento.getEmprestimo().getUsuario().getNome()).append("\n");
            relatorio.append("Valor: ").append(pagamento.getMulta()).append("\n\n");
        }

        return relatorio.toString();
    }


    public void calcularMulta (PagamentoMulta pagamento){
        List<PagamentoMulta> pagamentos = repositorioPagamento.listarPagamentos();
        float multa = 0;

        for (PagamentoMulta pag : pagamentos){
            if(pag.equals(pagamento)){
                //usar o getDataLimite?????????????????????????????????????????????????????????????????????????
                LocalDate dataPagamento = pag.getEmprestimo().getDataEmprestimo().plusDays(31);
                if (dataPagamento.isBefore(LocalDate.now())){
                    multa = 10;
                    if (dataPagamento.plusDays(7).isBefore(LocalDate.now())){
                        multa += 5;
                    }
                }
                pag.setMulta(multa);
            }
        }
    }

    public boolean pagarMulta(PagamentoMulta pag, float valor) throws ParametroInvalidoException {
        if(pag != null && valor != 0){
            for(PagamentoMulta pagMulta: listarPagamentosEmAtraso()){
                if(pagMulta.equals(pag)){
                    if(pagMulta.getMulta() == valor){
                        pagMulta.setMulta(0);
                        pagMulta.setStatusPagamento(true);
                        return true;
                    }
                }
            }
        } else {

            throw new ParametroInvalidoException();
        }
        return false;

    }



}
