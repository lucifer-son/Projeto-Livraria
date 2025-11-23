package cadastro.produto;

import model.Livro;
import repositorio.LivroRepositorioArray;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.util.List;

public class CadastroLivro {

    private LivroRepositorioArray repositorio;

    public CadastroLivro() {
        this.repositorio = LivroRepositorioArray.getInstance();
    }

    public void cadastrar(Livro livro) throws EntidadeJaExistenteExcecao {
        if (repositorio.buscarPorId(livro.getId()) != null) {
            throw new EntidadeJaExistenteExcecao(livro.getId(), "Já existe um livro com o ID " + livro.getId());
        }
        repositorio.inserir(livro);
    }

    public void atualizar(Livro livro) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(livro.getId()) == null) {
            throw new EntidadeNaoEncontradaExcecao(livro.getId(), "Livro não encontrado para atualização.");
        }
        repositorio.atualizar(livro);
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(id) == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "Livro não encontrado para remoção.");
        }
        repositorio.deletarPorId(id);
    }

    public Livro buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Livro> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public List<Livro> buscarPorAutor(String autor) {
        return repositorio.buscarPorAutor(autor);
    }

    public void listar() {
        for (Livro livro : repositorio.buscarTodos()) {
            System.out.println(livro);
        }
    }
}

