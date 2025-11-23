package repositorio;

import excecoes.EntidadeNaoEncontradaExcecao;
import model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoRepositorioArray {

    private static PedidoRepositorioArray instance;

    private Pedido[] pedidos;
    private int proximoIndice;
    private static int TAMANHO_MAXIMO = 100;

    private PedidoRepositorioArray() {
        this.pedidos = new Pedido[TAMANHO_MAXIMO];
        this.proximoIndice = 0;
    }

    public static PedidoRepositorioArray getInstance() {
        if (instance == null) {
            instance = new PedidoRepositorioArray();
        }
        return instance;
    }

    public void inserir(Pedido pedido) {
        if (proximoIndice < TAMANHO_MAXIMO) {
            pedidos[proximoIndice] = pedido;
            proximoIndice++;
        } else {
            System.err.println("Erro: Repositório de pedidos está cheio.");
        }
    }

    public void atualizar(Pedido pedido) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < proximoIndice; i++) {
            if (pedidos[i].getId().equals(pedido.getId())) {
                pedidos[i] = pedido;
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(pedido.getId(), "Pedido não encontrado para atualização.");
    }

    public Pedido buscarPorId(String id) {
        for (int i = 0; i < proximoIndice; i++) {
            if (pedidos[i] != null && pedidos[i].getId().equals(id)) {
                return pedidos[i];
            }
        }
        return null;
    }

    public List<Pedido> buscarTodos() {
        List<Pedido> todos = new ArrayList<>();
        for (int i = 0; i < proximoIndice; i++) {
            todos.add(pedidos[i]);
        }
        return todos;
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximoIndice; i++) {
            if (pedidos[i].getId().equals(id)) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            throw new EntidadeNaoEncontradaExcecao(id, "Pedido não encontrado para deleção.");
        }

        for (int i = indiceParaRemover; i < proximoIndice - 1; i++) {
            pedidos[i] = pedidos[i + 1];
        }

        proximoIndice--;
        pedidos[proximoIndice] = null;
    }

    public List<Pedido> buscarPorStatus(Pedido.StatusPedido status) {
        List<Pedido> pedidosComStatus = new ArrayList<>();
        for (int i = 0; i < proximoIndice; i++) {
            if (pedidos[i] != null && pedidos[i].getStatus() == status) {
                pedidosComStatus.add(pedidos[i]);
            }
        }
        return pedidosComStatus;
    }
}

