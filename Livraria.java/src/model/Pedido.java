package model;

import excecoes.StatusPedidoInvalidoExcecao;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Pedido {
    private String id;
    private String cliente;
    private Date data;
    private StatusPedido status;
    private Endereco endereco;
    private String metodoPagamento;
    private double frete;
    private double valorTotal;
    private List<ItemPedido> itens;
    private Pagamento pagamento;
    private Devolucao devolucao;

    public enum StatusPedido {
        PENDENTE,
        PROCESSANDO,
        ENVIADO,
        ENTREGUE,
        CANCELADO
    }

    private Pedido(Builder builder) {
        this.id = builder.id;
        this.cliente = builder.cliente;
        this.data = builder.data;
        this.status = builder.status;
        this.endereco = builder.endereco;
        this.metodoPagamento = builder.metodoPagamento;
        this.frete = builder.frete;
        this.valorTotal = builder.valorTotal;
        this.itens = builder.itens;
        this.pagamento = builder.pagamento;
        this.devolucao = builder.devolucao;
    }

    public String getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public Date getData() {
        return data;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido novoStatus) throws StatusPedidoInvalidoExcecao {
        if (this.status == StatusPedido.ENTREGUE || this.status == StatusPedido.CANCELADO) {
            throw new StatusPedidoInvalidoExcecao(this.id, this.status, novoStatus);
        }
        this.status = novoStatus;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public double getFrete() {
        return frete;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public Devolucao getDevolucao() {
        return devolucao;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", cliente='" + cliente + '\'' +
                ", data=" + data +
                ", status=" + status +
                ", valorTotal=" + valorTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class Builder {
        private final String id;
        private final String cliente;
        private final Date data;
        private StatusPedido status;
        private Endereco endereco;
        private String metodoPagamento;
        private double frete;
        private double valorTotal;
        private List<ItemPedido> itens;
        private Pagamento pagamento;
        private Devolucao devolucao;

        public Builder(String id, String cliente, Date data) {
            this.id = id;
            this.cliente = cliente;
            this.data = data;
            this.status = StatusPedido.PENDENTE;
        }

        public Builder withStatus(StatusPedido status) {
            this.status = status;
            return this;
        }

        public Builder withEndereco(Endereco endereco) {
            this.endereco = endereco;
            return this;
        }

        public Builder withMetodoPagamento(String metodoPagamento) {
            this.metodoPagamento = metodoPagamento;
            return this;
        }

        public Builder withFrete(double frete) {
            this.frete = frete;
            return this;
        }

        public Builder withValorTotal(double valorTotal) {
            this.valorTotal = valorTotal;
            return this;
        }

        public Builder withItens(List<ItemPedido> itens) {
            this.itens = itens;
            return this;
        }

        public Builder withPagamento(Pagamento pagamento) {
            this.pagamento = pagamento;
            return this;
        }

        public Builder withDevolucao(Devolucao devolucao) {
            this.devolucao = devolucao;
            return this;
        }

        public Pedido build() {
            return new Pedido(this);
        }
    }
}

