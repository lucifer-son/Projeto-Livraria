package cadastro;

import model.WishList;
import repositorio.WishListRepositorioArquivojson;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.util.List;

public class CadastroWishList {

    private WishListRepositorioArquivojson repositorio;

    public CadastroWishList() {
        this.repositorio = WishListRepositorioArquivojson.getInstance();
    }

    public void cadastrar(WishList wishList) throws EntidadeJaExistenteExcecao {
        if (repositorio.buscarPorId(wishList.getId()) != null) {
            throw new EntidadeJaExistenteExcecao(wishList.getId(), "Já existe uma WishList com o ID " + wishList.getId());
        }
        repositorio.inserir(wishList);
    }

    public void atualizar(WishList wishList) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(wishList.getId()) == null) {
            throw new EntidadeNaoEncontradaExcecao(wishList.getId(), "WishList não encontrada para atualização.");
        }
        repositorio.atualizar(wishList);
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(id) == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "WishList não encontrada para remoção.");
        }
        repositorio.deletarPorId(id);
    }

    public WishList buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<WishList> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public void listar() {
        for (WishList wishList : repositorio.buscarTodos()) {
            System.out.println(wishList);
        }
    }
}
