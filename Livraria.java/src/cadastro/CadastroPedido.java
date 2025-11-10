package cadastro;

import model.Pedido;
import repositorio.PedidoRepositorioArray;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.util.List;

public class CadastroPedido {

    private final PedidoRepositorioArray repositorio;

    public CadastroPedido() {
        this.repositorio = new PedidoRepositorioArray();
    }

    public void cadastrar(Pedido pedido) throws EntidadeJaExistenteExcecao {
        if (repositorio.buscarPorId(pedido.getId()) != null) {
            throw new EntidadeJaExistenteExcecao(pedido.getId(), "Já existe um pedido com o ID " + pedido.getId());
        }
        repositorio.inserir(pedido);
    }

    public void atualizar(Pedido pedido) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(pedido.getId()) == null) {
            throw new EntidadeNaoEncontradaExcecao(pedido.getId(), "Pedido não encontrado para atualização.");
        }
        repositorio.atualizar(pedido);
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(id) == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "Pedido não encontrado para remoção.");
        }
        repositorio.deletarPorId(id);
    }

    public Pedido buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Pedido> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public List<Pedido> buscarPorStatus(Pedido.StatusPedido status) {
        return repositorio.buscarPorStatus(status);
    }

    public void listar() {
        for (Pedido pedido : repositorio.buscarTodos()) {
            System.out.println(pedido);
        }
    }
}
