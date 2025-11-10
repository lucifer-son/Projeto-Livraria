package repositorio;

import excecoes.EntidadeNaoEncontradaExcecao;
import model.Pagamento;

import java.util.ArrayList;
import java.util.List;


public class PagamentoRepositorioArray {

    private final Pagamento[] pagamentos;
    private int proximoIndice;
    private static final int TAMANHO_MAXIMO = 100;

    public PagamentoRepositorioArray() {
        this.pagamentos = new Pagamento[TAMANHO_MAXIMO];
        this.proximoIndice = 0;
    }

    public void inserir(Pagamento pagamento) {
        if (proximoIndice < TAMANHO_MAXIMO) {
            pagamentos[proximoIndice] = pagamento;
            proximoIndice++;
        } else {
            System.err.println("Erro: Repositório de pagamentos está cheio.");
        }
    }

    public void atualizar(Pagamento pagamento) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < proximoIndice; i++) {
            if (pagamentos[i].getId().equals(pagamento.getId())) {
                pagamentos[i] = pagamento;
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(pagamento.getId(), "Pagamento não encontrado para atualização.");
    }

    
    public Pagamento buscarPorId(String id) {
        for (int i = 0; i < proximoIndice; i++) {
            if (pagamentos[i] != null && pagamentos[i].getId().equals(id)) {
                return pagamentos[i]; // Retorna o Pagamento diretamente
            }
        }
        return null; 
    }

    public List<Pagamento> buscarTodos() {
        List<Pagamento> todos = new ArrayList<>();
        for (int i = 0; i < proximoIndice; i++) {
            todos.add(pagamentos[i]);
        }
        return todos;
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximoIndice; i++) {
            if (pagamentos[i].getId().equals(id)) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            throw new EntidadeNaoEncontradaExcecao(id, "Pagamento não encontrado para deleção.");
        }

        for (int i = indiceParaRemover; i < proximoIndice - 1; i++) {
            pagamentos[i] = pagamentos[i + 1];
        }

        proximoIndice--;
        pagamentos[proximoIndice] = null;
    }
}

