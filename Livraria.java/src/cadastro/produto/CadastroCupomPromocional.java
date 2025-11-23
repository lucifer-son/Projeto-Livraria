package cadastro.produto;

import model.CupomPromocional;
import repositorio.CupomPromocionalRepositorioArray;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.util.List;

public class CadastroCupomPromocional {

    private CupomPromocionalRepositorioArray repositorio;

    public CadastroCupomPromocional() {
        this.repositorio = CupomPromocionalRepositorioArray.getInstance();
    }

    public void cadastrar(CupomPromocional cupom) throws EntidadeJaExistenteExcecao {
        if (repositorio.buscarPorId(cupom.getId()) != null) {
            throw new EntidadeJaExistenteExcecao(cupom.getId(), "Já existe um cupom com o ID " + cupom.getId());
        }
        repositorio.inserir(cupom);
    }

    public void atualizar(CupomPromocional cupom) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(cupom.getId()) == null) {
            throw new EntidadeNaoEncontradaExcecao(cupom.getId(), "Cupom não encontrado para atualização.");
        }
        repositorio.atualizar(cupom);
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        if (repositorio.buscarPorId(id) == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "Cupom não encontrado para remoção.");
        }
        repositorio.deletarPorId(id);
    }

    public CupomPromocional buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<CupomPromocional> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public CupomPromocional buscarPorCodigo(String codigo) {
        return repositorio.buscarPorCodigo(codigo);
    }

    public void listar() {
        for (CupomPromocional cupom : repositorio.buscarTodos()) {
            System.out.println(cupom);
        }
    }
}

