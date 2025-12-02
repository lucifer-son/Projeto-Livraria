package cadastro.produto;

import model.Avaliacao;
import repositorio.AvaliacaoRepositorioArquivojson;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.util.List;

public class CadastroAvaliacao {

    private AvaliacaoRepositorioArquivojson repositorio;

    public CadastroAvaliacao() {
        this.repositorio = AvaliacaoRepositorioArquivojson.getInstance();
    }

    public void cadastrar(Avaliacao avaliacao) throws EntidadeJaExistenteExcecao {
        if (repositorio.buscarPorId(avaliacao.getId()) != null) {
            throw new EntidadeJaExistenteExcecao(avaliacao.getId(), "Já existe uma avaliação com o ID " + avaliacao.getId());
        }
        repositorio.inserir(avaliacao);
    }

    public void atualizar(Avaliacao avaliacao) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(avaliacao.getId()) == null) {
            throw new EntidadeNaoEncontradaExcecao(avaliacao.getId(), "Avaliação não encontrada para atualização.");
        }
        repositorio.atualizar(avaliacao);
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(id) == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "Avaliação não encontrada para remoção.");
        }
        repositorio.deletarPorId(id);
    }

    public Avaliacao buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Avaliacao> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public void listar() {
        for (Avaliacao avaliacao : repositorio.buscarTodos()) {
            System.out.println(avaliacao);
        }
    }
}


