package model;

import java.util.Objects;
import java.util.UUID;
import excecoes.QuantidadeInvalidaExcecao;
import excecoes.PrecoInvalidoExcecao;

public class ItemPedido {
    private String id;
    private Livro livro;
    private int quantidade;
    private double precoUnitario;

    public ItemPedido(Livro livro, int quantidade) throws QuantidadeInvalidaExcecao, PrecoInvalidoExcecao {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaExcecao("A quantidade do item do pedido deve ser maior que zero.");
        }
        if (livro.getPreco() <= 0) {
            throw new PrecoInvalidoExcecao("O preço unitário do item do pedido deve ser maior que zero.");
        }
        this.id = UUID.randomUUID().toString();
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoUnitario = livro.getPreco();
    }

    public double getSubtotal() {
        return quantidade * precoUnitario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) throws PrecoInvalidoExcecao {
        if (precoUnitario <= 0) {
            throw new PrecoInvalidoExcecao("O preço unitário do item do pedido deve ser maior que zero.");
        }
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id='" + id + '\'' +
                ", livro='" + livro.getTitulo() + '\'' +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
