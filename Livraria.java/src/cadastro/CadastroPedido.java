package cadastro;

import model.Pedido;
import repositorio.PedidoRepositorioArquivojson;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CadastroPedido {

    private PedidoRepositorioArquivojson repositorio;

    public CadastroPedido() {
        this.repositorio = PedidoRepositorioArquivojson.getInstance();
    }

    public void cadastrar(Pedido pedido) throws EntidadeJaExistenteExcecao {
        if (repositorio.buscarPorId(pedido.getId()) != null) {
            throw new EntidadeJaExistenteExcecao(pedido.getId(), "Já existe um pedido com o ID " + pedido.getId());
        }
        repositorio.inserir(pedido);
    }

    public void atualizar(Pedido pedido) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(pedido.getId()) == null) {
            throw new EntidadeNaoEncontradaExcecao(pedido.getId(), "Pedido não encontrado para atualização.");
        }
        repositorio.atualizar(pedido);
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(id) == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "Pedido não encontrado para remoção.");
        }
        repositorio.deletarPorId(id);
    }

    public Pedido buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Pedido> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public List<Pedido> buscarPorStatus(Pedido.StatusPedido status) {
        return repositorio.buscarPorStatus(status);
    }

    public void listar() {
        for (Pedido pedido : repositorio.buscarTodos()) {
            System.out.println(pedido);
        }
    }

    public List<String> gerarRelatorioVendas(LocalDate inicio, LocalDate fim, String tipo) {

        List<Pedido> pedidos = repositorio.buscarTodos();
        List<String> resultado = new ArrayList<>();
        LocalDate dataAtual = inicio;

        while (!dataAtual.isAfter(fim)) {

            LocalDate periodoFim;

            if (tipo.equalsIgnoreCase("diario")) {
                periodoFim = dataAtual;

            } else if (tipo.equalsIgnoreCase("semanal")) {
                periodoFim = dataAtual.plusDays(6);

            } else if (tipo.equalsIgnoreCase("mensal")) {
                periodoFim = dataAtual.withDayOfMonth(dataAtual.lengthOfMonth());

            } else {
                resultado.add("Tipo inválido! Use: diario, semanal ou mensal.");
                return resultado;
            }

            if (periodoFim.isAfter(fim)) {
                periodoFim = fim;
            }

            int quantidade = 0;
            double total = 0;

            for (Pedido p : pedidos) {
                LocalDate dataPedido = p.getData().toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate();

                if (!dataPedido.isBefore(dataAtual) && !dataPedido.isAfter(periodoFim)) {
                    quantidade++;
                    total += p.getValorTotal();
                }
            }

            String linha;

            if (tipo.equalsIgnoreCase("diario")) {
                linha = "Dia " + dataAtual +
                        " | Pedidos: " + quantidade +
                        " | Receita: R$ " + total;

            } else if (tipo.equalsIgnoreCase("semanal")) {
                linha = "Semana " + dataAtual + " até " + periodoFim +
                        " | Pedidos: " + quantidade +
                        " | Receita: R$ " + total;

            } else {
                linha = "Mês " + dataAtual.getMonthValue() + "/" + dataAtual.getYear() +
                        " | Pedidos: " + quantidade +
                        " | Receita: R$ " + total;
            }

            resultado.add(linha);

            dataAtual = periodoFim.plusDays(1);
        }

        return resultado;
    }
}
