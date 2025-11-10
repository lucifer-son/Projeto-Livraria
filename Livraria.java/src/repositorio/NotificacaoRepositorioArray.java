package repositorio;

import excecoes.EntidadeNaoEncontradaExcecao;
import model.Notificacao;

import java.util.ArrayList;
import java.util.List;
// import java.util.Optional; // Removido

public class NotificacaoRepositorioArray {

    private final Notificacao[] notificacoes;
    private int proximoIndice;
    private static final int TAMANHO_MAXIMO = 100;

    public NotificacaoRepositorioArray() {
        this.notificacoes = new Notificacao[TAMANHO_MAXIMO];
        this.proximoIndice = 0;
    }

    public void inserir(Notificacao notificacao) {
        if (proximoIndice < TAMANHO_MAXIMO) {
            notificacoes[proximoIndice] = notificacao;
            proximoIndice++;
        } else {
            System.err.println("Erro: Repositório de notificações está cheio.");
        }
    }

    public void atualizar(Notificacao notificacao) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < proximoIndice; i++) {
            if (notificacoes[i].getId().equals(notificacao.getId())) {
                notificacoes[i] = notificacao;
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(notificacao.getId(), "Notificação não encontrada para atualização.");
    }

    // Retorno alterado de Optional<Notificacao> para Notificacao
    public Notificacao buscarPorId(String id) {
        for (int i = 0; i < proximoIndice; i++) {
            if (notificacoes[i] != null && notificacoes[i].getId().equals(id)) {
                return notificacoes[i]; // Retorna a Notificacao diretamente
            }
        }
        return null; // Retorna null se não encontrar
    }

    public List<Notificacao> buscarTodos() {
        List<Notificacao> todos = new ArrayList<>();
        for (int i = 0; i < proximoIndice; i++) {
            todos.add(notificacoes[i]);
        }
        return todos;
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximoIndice; i++) {
            if (notificacoes[i].getId().equals(id)) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            throw new EntidadeNaoEncontradaExcecao(id, "Notificação não encontrada para deleção.");
        }

        for (int i = indiceParaRemover; i < proximoIndice - 1; i++) {
            notificacoes[i] = notificacoes[i + 1];
        }

        proximoIndice--;
        notificacoes[proximoIndice] = null;
    }
}
