package cadastro.produto;

import model.Carrinho;
import repositorio.CarrinhoRepositorioArray;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.util.List;

public class CadastroCarrinho {

    private CarrinhoRepositorioArray repositorio;

    public CadastroCarrinho() {
        this.repositorio = CarrinhoRepositorioArray.getInstance();
    }

    public void cadastrar(Carrinho carrinho) throws EntidadeJaExistenteExcecao {
        if (repositorio.buscarPorId(carrinho.getId()) != null) {
            throw new EntidadeJaExistenteExcecao(carrinho.getId(), "Já existe um carrinho com o ID " + carrinho.getId());
        }
        repositorio.inserir(carrinho);
    }

    public void atualizar(Carrinho carrinho) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(carrinho.getId()) == null) {
            throw new EntidadeNaoEncontradaExcecao(carrinho.getId(), "Carrinho não encontrado para atualização.");
        }
        repositorio.atualizar(carrinho);
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(id) == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "Carrinho não encontrado para remoção.");
        }
        repositorio.deletarPorId(id);
    }

    public Carrinho buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Carrinho> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public void listar() {
        for (Carrinho carrinho : repositorio.buscarTodos()) {
            System.out.println(carrinho);
        }
    }
}

