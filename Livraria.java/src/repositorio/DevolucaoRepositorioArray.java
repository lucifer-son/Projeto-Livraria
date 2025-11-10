package repositorio;

import excecoes.EntidadeNaoEncontradaExcecao;
import model.Devolucao;

import java.util.ArrayList;
import java.util.List;

public class DevolucaoRepositorioArray {

    private final Devolucao[] devolucoes;
    private int proximoIndice;
    private static final int TAMANHO_MAXIMO = 100;

    public DevolucaoRepositorioArray() {
        this.devolucoes = new Devolucao[TAMANHO_MAXIMO];
        this.proximoIndice = 0;
    }

    public void inserir(Devolucao devolucao) {
        if (proximoIndice < TAMANHO_MAXIMO) {
            devolucoes[proximoIndice] = devolucao;
            proximoIndice++;
        } else {
            System.err.println("Erro: Repositório de devoluções está cheio.");
        }
    }

    public void atualizar(Devolucao devolucao) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < proximoIndice; i++) {
            if (devolucoes[i].getId().equals(devolucao.getId())) {
                devolucoes[i] = devolucao;
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(devolucao.getId(), "Devolução não encontrada para atualização.");
    }

    public Devolucao buscarPorId(String id) {
        for (int i = 0; i < proximoIndice; i++) {
            if (devolucoes[i] != null && devolucoes[i].getId().equals(id)) {
                return devolucoes[i];
            }
        }
        return null;
    }

    public List<Devolucao> buscarTodos() {
        List<Devolucao> todos = new ArrayList<>();
        for (int i = 0; i < proximoIndice; i++) {
            todos.add(devolucoes[i]);
        }
        return todos;
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximoIndice; i++) {
            if (devolucoes[i].getId().equals(id)) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            throw new EntidadeNaoEncontradaExcecao(id, "Devolução não encontrada para deleção.");
        }

        for (int i = indiceParaRemover; i < proximoIndice - 1; i++) {
            devolucoes[i] = devolucoes[i + 1];
        }

        proximoIndice--;
        devolucoes[proximoIndice] = null;
    }
}
