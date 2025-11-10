package repositorio;

import excecoes.EntidadeNaoEncontradaExcecao;
import model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroRepositorioArray {

    private final Livro[] livros;
    private int proximoIndice;
    private static final int TAMANHO_MAXIMO = 100;

    public LivroRepositorioArray() {
        this.livros = new Livro[TAMANHO_MAXIMO];
        this.proximoIndice = 0;
    }

    public void inserir(Livro livro) {
        if (proximoIndice < TAMANHO_MAXIMO) {
            livros[proximoIndice] = livro;
            proximoIndice++;
        } else {
            System.err.println("Erro: Repositório de livros está cheio.");
        }
    }

    public void atualizar(Livro livro) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < proximoIndice; i++) {
            if (livros[i].getId().equals(livro.getId())) {
                livros[i] = livro;
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(livro.getId(), "Livro não encontrado para atualização.");
    }

    public Livro buscarPorId(String id) {
        for (int i = 0; i < proximoIndice; i++) {
            if (livros[i] != null && livros[i].getId().equals(id)) {
                return livros[i];
            }
        }
        return null;
    }

    public List<Livro> buscarTodos() {
        List<Livro> todos = new ArrayList<>();
        for (int i = 0; i < proximoIndice; i++) {
            todos.add(livros[i]);
        }
        return todos;
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximoIndice; i++) {
            if (livros[i].getId().equals(id)) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            throw new EntidadeNaoEncontradaExcecao(id, "Livro não encontrado para deleção.");
        }

        for (int i = indiceParaRemover; i < proximoIndice - 1; i++) {
            livros[i] = livros[i + 1];
        }

        proximoIndice--;
        livros[proximoIndice] = null;
    }

    public List<Livro> buscarPorAutor(String autor) {
        List<Livro> livrosDoAutor = new ArrayList<>();
        for (int i = 0; i < proximoIndice; i++) {
            if (livros[i] != null && livros[i].getAutores().contains(autor)) {
                livrosDoAutor.add(livros[i]);
            }
        }
        return livrosDoAutor;
    }
}
