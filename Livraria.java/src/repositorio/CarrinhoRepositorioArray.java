package repositorio;

import excecoes.EntidadeNaoEncontradaExcecao;
import model.Carrinho;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoRepositorioArray {

    private final Carrinho[] carrinhos;
    private int proximoIndice;
    private static final int TAMANHO_MAXIMO = 100;

    public CarrinhoRepositorioArray() {
        this.carrinhos = new Carrinho[TAMANHO_MAXIMO];
        this.proximoIndice = 0;
    }

    public void inserir(Carrinho carrinho) {
        if (proximoIndice < TAMANHO_MAXIMO) {
            carrinhos[proximoIndice] = carrinho;
            proximoIndice++;
        } else {
            System.err.println("Erro: Repositório de carrinhos está cheio.");
        }
    }

    public void atualizar(Carrinho carrinho) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < proximoIndice; i++) {
            if (carrinhos[i].getId().equals(carrinho.getId())) {
                carrinhos[i] = carrinho;
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(carrinho.getId(), "Carrinho não encontrado para atualização.");
    }

    public Carrinho buscarPorId(String id) {
        for (int i = 0; i < proximoIndice; i++) {
            if (carrinhos[i] != null && carrinhos[i].getId().equals(id)) {
                return carrinhos[i];
            }
        }
        return null;
    }

    public List<Carrinho> buscarTodos() {
        List<Carrinho> todos = new ArrayList<>();
        for (int i = 0; i < proximoIndice; i++) {
            todos.add(carrinhos[i]);
        }
        return todos;
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximoIndice; i++) {
            if (carrinhos[i].getId().equals(id)) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            throw new EntidadeNaoEncontradaExcecao(id, "Carrinho não encontrado para deleção.");
        }

        for (int i = indiceParaRemover; i < proximoIndice - 1; i++) {
            carrinhos[i] = carrinhos[i + 1];
        }

        proximoIndice--;
        carrinhos[proximoIndice] = null;
    }
}
