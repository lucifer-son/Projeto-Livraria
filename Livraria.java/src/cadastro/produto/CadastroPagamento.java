package cadastro.produto;

import model.Pagamento;
import repositorio.PagamentoRepositorioArray;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.util.List;

public class CadastroPagamento {

    private final PagamentoRepositorioArray repositorio;

    public CadastroPagamento() {
        this.repositorio = new PagamentoRepositorioArray();
    }

    public void cadastrar(Pagamento pagamento) throws EntidadeJaExistenteExcecao {
        if (repositorio.buscarPorId(pagamento.getId()) != null) {
            throw new EntidadeJaExistenteExcecao(pagamento.getId(), "Já existe um pagamento com o ID " + pagamento.getId());
        }
        repositorio.inserir(pagamento);
    }

    public void atualizar(Pagamento pagamento) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(pagamento.getId()) == null) {
            throw new EntidadeNaoEncontradaExcecao(pagamento.getId(), "Pagamento não encontrado para atualização.");
        }
        repositorio.atualizar(pagamento);
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(id) == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "Pagamento não encontrado para remoção.");
        }
        repositorio.deletarPorId(id);
    }

    public Pagamento buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Pagamento> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public void listar() {
        for (Pagamento pagamento : repositorio.buscarTodos()) {
            System.out.println(pagamento);
        }
    }
}
