package cadastro.produto;

import model.Carrinho;
import model.Livro;
import repositorio.CarrinhoRepositorioArquivojson;

import java.util.Map;

public class CadastroCarrinho {

    private final CarrinhoRepositorioArquivojson repositorio;

    public CadastroCarrinho() {
        this.repositorio = CarrinhoRepositorioArquivojson.getInstance();
    }

    public void adicionarItem(Livro livro, int quantidade) {
        Carrinho.getInstance().adicionarItem(livro, quantidade);
        repositorio.salvar(Carrinho.getInstance());
    }

    public void removerItem(Livro livro) {
        Carrinho.getInstance().removerItem(livro);
        repositorio.salvar(Carrinho.getInstance());
    }

    public Map<Livro, Integer> getItens() {
        return Carrinho.getInstance().getItens();
    }

    public void limparCarrinho() {
        Carrinho.getInstance().limparCarrinho();
        repositorio.salvar(Carrinho.getInstance());
    }

    public double getValorTotal() {
        return Carrinho.getInstance().getValorTotal();
    }
}
