package repositorio;

import excecoes.EntidadeNaoEncontradaExcecao;
import model.WishList;

import java.util.ArrayList;
import java.util.List;

public class WishListRepositorioArray {

    private static WishListRepositorioArray instance;

    private WishList[] wishLists;
    private int proximoIndice;
    private static int TAMANHO_MAXIMO = 100;

    private WishListRepositorioArray() {
        this.wishLists = new WishList[TAMANHO_MAXIMO];
        this.proximoIndice = 0;
    }

    public static WishListRepositorioArray getInstance() {
        if (instance == null) {
            instance = new WishListRepositorioArray();
        }
        return instance;
    }

    public void inserir(WishList wishList) {
        if (proximoIndice < TAMANHO_MAXIMO) {
            wishLists[proximoIndice] = wishList;
            proximoIndice++;
        } else {
            System.err.println("Erro: Repositório de wishlists está cheio.");
        }
    }

    public void atualizar(WishList wishList) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < proximoIndice; i++) {
            if (wishLists[i].getId().equals(wishList.getId())) {
                wishLists[i] = wishList;
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(wishList.getId(), "WishList não encontrada para atualização.");
    }

    public WishList buscarPorId(String id) {
        for (int i = 0; i < proximoIndice; i++) {
            if (wishLists[i] != null && wishLists[i].getId().equals(id)) {
                return wishLists[i];
            }
        }
        return null;
    }

    public List<WishList> buscarTodos() {
        List<WishList> todos = new ArrayList<>();
        for (int i = 0; i < proximoIndice; i++) {
            todos.add(wishLists[i]);
        }
        return todos;
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximoIndice; i++) {
            if (wishLists[i].getId().equals(id)) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            throw new EntidadeNaoEncontradaExcecao(id, "WishList não encontrada para deleção.");
        }

        for (int i = indiceParaRemover; i < proximoIndice - 1; i++) {
            wishLists[i] = wishLists[i + 1];
        }

        proximoIndice--;
        wishLists[proximoIndice] = null;
    }
}
