package model;

import java.util.List;
import java.util.Objects;

public class Carrinho {
    private String id;
    private List<ItemPedido> itens;
    private double valorTotal;

    public Carrinho(String id, List<ItemPedido> itens) {
        this.id = id;
        this.itens = itens;
        this.valorTotal = calcularValorTotal();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    private double calcularValorTotal() {
        if (itens == null) {
            return 0.0;
        }
        double total = 0.0;
        for (ItemPedido item : itens) {
            total += item.getPrecoUnitario() * item.getQuantidade();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "id='" + id + '\'' +
                ", numeroDeItens=" + (itens != null ? itens.size() : 0) +
                ", valorTotal=" + valorTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrinho carrinho = (Carrinho) o;
        return Objects.equals(id, carrinho.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
