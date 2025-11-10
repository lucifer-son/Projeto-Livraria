package repositorio;

import excecoes.EntidadeNaoEncontradaExcecao;
import model.Avaliacao;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoRepositorioArray {

    private final Avaliacao[] avaliacoes;
    private int proximoIndice;
    private static final int TAMANHO_MAXIMO = 100;

    public AvaliacaoRepositorioArray() {
        this.avaliacoes = new Avaliacao[TAMANHO_MAXIMO];
        this.proximoIndice = 0;
    }

    public void inserir(Avaliacao avaliacao) {
        if (proximoIndice < TAMANHO_MAXIMO) {
            avaliacoes[proximoIndice] = avaliacao;
            proximoIndice++;
        } else {
            System.err.println("Erro: Repositório de avaliações está cheio.");
        }
    }

    public void atualizar(Avaliacao avaliacao) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < proximoIndice; i++) {
            if (avaliacoes[i].getId().equals(avaliacao.getId())) {
                avaliacoes[i] = avaliacao;
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(avaliacao.getId(), "Avaliação não encontrada para atualização.");
    }

    public Avaliacao buscarPorId(String id) {
        for (int i = 0; i < proximoIndice; i++) {
            if (avaliacoes[i] != null && avaliacoes[i].getId().equals(id)) {
                return avaliacoes[i];
            }
        }
        return null;
    }

    public List<Avaliacao> buscarTodos() {
        List<Avaliacao> todos = new ArrayList<>();
        for (int i = 0; i < proximoIndice; i++) {
            todos.add(avaliacoes[i]);
        }
        return todos;
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximoIndice; i++) {
            if (avaliacoes[i].getId().equals(id)) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            throw new EntidadeNaoEncontradaExcecao(id, "Avaliação não encontrada para deleção.");
        }

        for (int i = indiceParaRemover; i < proximoIndice - 1; i++) {
            avaliacoes[i] = avaliacoes[i + 1];
        }

        proximoIndice--;
        avaliacoes[proximoIndice] = null;
    }
}

