package repositorio;

import excecoes.EntidadeNaoEncontradaExcecao;
import model.CupomPromocional;

import java.util.ArrayList;
import java.util.List;
// import java.util.Optional; // Removido

public class CupomPromocionalRepositorioArray {

    private final CupomPromocional[] cupons;
    private int proximoIndice;
    private static final int TAMANHO_MAXIMO = 100;

    public CupomPromocionalRepositorioArray() {
        this.cupons = new CupomPromocional[TAMANHO_MAXIMO];
        this.proximoIndice = 0;
    }

    public void inserir(CupomPromocional cupom) {
        if (proximoIndice < TAMANHO_MAXIMO) {
            cupons[proximoIndice] = cupom;
            proximoIndice++;
        } else {
            System.err.println("Erro: Repositório de cupons está cheio.");
        }
    }

    public void atualizar(CupomPromocional cupom) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < proximoIndice; i++) {
            if (cupons[i].getId().equals(cupom.getId())) {
                cupons[i] = cupom;
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(cupom.getId(), "Cupom não encontrado para atualização.");
    }

    // Retorno alterado de Optional<CupomPromocional> para CupomPromocional
    public CupomPromocional buscarPorId(String id) {
        for (int i = 0; i < proximoIndice; i++) {
            if (cupons[i] != null && cupons[i].getId().equals(id)) {
                return cupons[i]; // Retorna o CupomPromocional diretamente
            }
        }
        return null; // Retorna null se não encontrar
    }

    public List<CupomPromocional> buscarTodos() {
        List<CupomPromocional> todos = new ArrayList<>();
        for (int i = 0; i < proximoIndice; i++) {
            todos.add(cupons[i]);
        }
        return todos;
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximoIndice; i++) {
            if (cupons[i].getId().equals(id)) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            throw new EntidadeNaoEncontradaExcecao(id, "Cupom não encontrado para deleção.");
        }

        for (int i = indiceParaRemover; i < proximoIndice - 1; i++) {
            cupons[i] = cupons[i + 1];
        }

        proximoIndice--;
        cupons[proximoIndice] = null;
    }

    // Retorno alterado de Optional<CupomPromocional> para CupomPromocional
    public CupomPromocional buscarPorCodigo(String codigo) {
        for (int i = 0; i < proximoIndice; i++) {
            if (cupons[i] != null && cupons[i].getCodigo().equalsIgnoreCase(codigo)) {
                return cupons[i]; // Retorna o CupomPromocional diretamente
            }
        }
        return null; // Retorna null se não encontrar
    }
}
