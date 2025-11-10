package excecoes;

import model.Pedido;

public class StatusPedidoInvalidoExcecao extends Exception {
    private final String idPedido;
    private final Pedido.StatusPedido statusAtual;
    private final Pedido.StatusPedido statusTentado;

    public StatusPedidoInvalidoExcecao(String idPedido, Pedido.StatusPedido statusAtual, Pedido.StatusPedido statusTentado) {
        super("Transição de status inválida para o pedido ID " + idPedido +
              ": de " + statusAtual + " para " + statusTentado + ".");
        this.idPedido = idPedido;
        this.statusAtual = statusAtual;
        this.statusTentado = statusTentado;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public Pedido.StatusPedido getStatusAtual() {
        return statusAtual;
    }

    public Pedido.StatusPedido getStatusTentado() {
        return statusTentado;
    }
}
