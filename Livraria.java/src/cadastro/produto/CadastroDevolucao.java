package cadastro.produto;

import model.Devolucao;
import repositorio.DevolucaoRepositorioArquivojson;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.util.List;

public class CadastroDevolucao {

    private DevolucaoRepositorioArquivojson repositorio;

    public CadastroDevolucao() {
        this.repositorio = DevolucaoRepositorioArquivojson.getInstance();
    }

    public void cadastrar(Devolucao devolucao) throws EntidadeJaExistenteExcecao {
        if (repositorio.buscarPorId(devolucao.getId()) != null) {
            throw new EntidadeJaExistenteExcecao(devolucao.getId(), "Já existe uma devolução com o ID " + devolucao.getId());
        }
        repositorio.inserir(devolucao);
    }

    public void atualizar(Devolucao devolucao) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(devolucao.getId()) == null) {
            throw new EntidadeNaoEncontradaExcecao(devolucao.getId(), "Devolução não encontrada para atualização.");
        }
        repositorio.atualizar(devolucao);
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(id) == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "Devolução não encontrada para remoção.");
        }
        repositorio.deletarPorId(id);
    }

    public Devolucao buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Devolucao> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public void listar() {
        for (Devolucao devolucao : repositorio.buscarTodos()) {
            System.out.println(devolucao);
        }
    }
}


