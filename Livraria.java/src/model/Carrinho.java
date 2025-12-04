package model;

import excecoes.PrecoInvalidoExcecao;
import excecoes.QuantidadeInvalidaExcecao;

import java.util.HashMap;
import java.util.Map;

public class Carrinho {

    private static Carrinho instance;
    private final Map<Livro, Integer> itens;

    private Carrinho() {
        itens = new HashMap<>();
    }

    public static Carrinho getInstance() {
        if (instance == null) {
            instance = new Carrinho();
        }
        return instance;
    }

    public void adicionarItem(Livro livro, int quantidade) {
        if (quantidade <= 0) {

            System.err.println("Tentativa de adicionar quantidade inválida (<= 0) ao carrinho.");
            return;
        }

        itens.merge(livro, quantidade, Integer::sum);
    }

    public void removerItem(Livro livro) {
        itens.remove(livro);
    }

    public void removerItem(Livro livro, int quantidade) {
        if (quantidade <= 0) {
            System.err.println("Tentativa de remover quantidade inválida (<= 0) do carrinho.");
            return;
        }

        itens.computeIfPresent(livro, (k, v) -> {
            int novaQuantidade = v - quantidade;
            if (novaQuantidade <= 0) {
                return null;
            }
            return novaQuantidade;
        });
    }


    public Map<Livro, Integer> getItens() {
        return itens;
    }

    public void limparCarrinho() {
        itens.clear();
    }

    public double getValorTotal() {
        return itens.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPreco() * entry.getValue())
                .sum();
    }
}
