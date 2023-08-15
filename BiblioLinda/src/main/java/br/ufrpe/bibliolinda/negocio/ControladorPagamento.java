package br.ufrpe.bibliolinda.negocio;

import br.ufrpe.bibliolinda.beans.Cliente;
import br.ufrpe.bibliolinda.beans.PagamentoMulta;
import br.ufrpe.bibliolinda.dados.RepositorioPagamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorPagamento {

    private static ControladorPagamento instancia;

    private final RepositorioPagamento repositorioPagamento;

    private ControladorPagamento() {
        this.repositorioPagamento = new RepositorioPagamento();
    }

    public ControladorPagamento getInstancia() {
        if (instancia == null)
            instancia = new ControladorPagamento();
        return instancia;
    }

    public List<PagamentoMulta> listarPagamentos() {
        return repositorioPagamento.listarPagamentos();
    }

    public void adicionarPagamento(PagamentoMulta pagamento) {
        if (!listarPagamentos().contains(pagamento) && pagamento != null
                && pagamento.getEmprestimo() != null && pagamento.getMulta() != 0
                && pagamento.getDataDePagamento() != null)
            repositorioPagamento.adicionarPagamento(pagamento);
        else {
            // exceção
        }
    }

    public void removerPagamento(PagamentoMulta pagamento) {
        if (listarPagamentos().contains(pagamento) && pagamento != null)
            repositorioPagamento.removerPendencia(pagamento);
        else {
            // PagamentoNaoExisteException
        }
    }

    public void editarPagamento(PagamentoMulta pagamento, PagamentoMulta novoPagamento) {
        if (listarPagamentos().contains(pagamento)) {
            if (novoPagamento != null && novoPagamento.getEmprestimo() != null
                    && novoPagamento.getMulta() != 0 && novoPagamento.getDataDePagamento() != null) {
                repositorioPagamento.editarPagamento(pagamento, novoPagamento);
            } else {
                // exceção informando que os atributos são inválidos
            }
        } else {
            // PagamentoNaoExisteException
        }
    }

    public List<PagamentoMulta> listarPagamentosPorCliente(Cliente cliente) {
        List<PagamentoMulta> resultado = new ArrayList<>();

        if (cliente != null)
            for (PagamentoMulta pagamento : repositorioPagamento.listarPagamentos())
                if (pagamento.getEmprestimo().getCliente().equals(cliente))
                    resultado.add(pagamento);
        else {
            // lançar exceção
        }

        return resultado;
    }

    public List<PagamentoMulta> listarPagamentosEmAtraso() {
        List<PagamentoMulta> resultado = new ArrayList<>();

        for (PagamentoMulta pagamento : repositorioPagamento.listarPagamentos())
            if (!pagamento.getStatusPagamento())
                resultado.add(pagamento);

        return resultado;
    }

    public List<PagamentoMulta> listarPagamentosEntrePeriodo(LocalDate inicio, LocalDate fim) {
        List<PagamentoMulta> resultado = new ArrayList<>();

        for (PagamentoMulta pagamento : repositorioPagamento.listarPagamentos())
            if (pagamento.getDataDePagamento().isAfter(inicio) && pagamento.getDataDePagamento().isBefore(fim))
                resultado.add(pagamento);

        return resultado;
    }

    public List<PagamentoMulta> listarPagamentosAcimaDeValor(float valor) {
        List<PagamentoMulta> resultado = new ArrayList<>();

        for (PagamentoMulta pagamento : repositorioPagamento.listarPagamentos())
            if (pagamento.getMulta() >= valor)
                resultado.add(pagamento);

        return resultado;
    }

    public float calcularValorTotalDePagamentos() {
        float total = 0;
        List<PagamentoMulta> pagamentos = repositorioPagamento.listarPagamentos();

        if (pagamentos != null) {
            for (PagamentoMulta pagamento : pagamentos)
                total += pagamento.getMulta();
        }

        return total;
    }

    public int calcularNumeroTotalDePagamentos() {
        return repositorioPagamento.listarPagamentos().size();
    }

    public String gerarRelatorioDePagamentos(LocalDate inicio, LocalDate fim) {
        List<PagamentoMulta> pagamentos = listarPagamentosEntrePeriodo(inicio, fim);

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Pagamentos\n");
        relatorio.append("Período: ").append(inicio).append(" a ").append(fim).append("\n\n");

        for (PagamentoMulta pagamento : pagamentos) {
            relatorio.append("Data: ").append(pagamento.getDataDePagamento()).append("\n");
            relatorio.append("Cliente: ").append(pagamento.getEmprestimo().getCliente().getNome()).append("\n");
            relatorio.append("Valor: ").append(pagamento.getMulta()).append("\n\n");
        }

        return relatorio.toString();
    }
}
