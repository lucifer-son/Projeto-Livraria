package cadastro.produto;

import model.Notificacao;
import repositorio.NotificacaoRepositorioArray;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.util.List;


public class CadastroNotificacao {

    private final NotificacaoRepositorioArray repositorio;

    public CadastroNotificacao() {
        this.repositorio = new NotificacaoRepositorioArray();
    }

    public void cadastrar(Notificacao notificacao) throws EntidadeJaExistenteExcecao {
        
        if (repositorio.buscarPorId(notificacao.getId()) != null) {
            throw new EntidadeJaExistenteExcecao(notificacao.getId(), "Já existe uma notificação com o ID " + notificacao.getId());
        }
        repositorio.inserir(notificacao);
    }

    public void atualizar(Notificacao notificacao) throws EntidadeNaoEncontradaExcecao {
        
        if (repositorio.buscarPorId(notificacao.getId()) == null) {
            throw new EntidadeNaoEncontradaExcecao(notificacao.getId(), "Notificação não encontrada para atualização.");
        }
        repositorio.atualizar(notificacao);
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        
        if (repositorio.buscarPorId(id) == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "Notificação não encontrada para remoção.");
        }
        repositorio.deletarPorId(id);
    }

   
    public Notificacao buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Notificacao> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public void listar() {
        for (Notificacao notificacao : repositorio.buscarTodos()) {
            System.out.println(notificacao);
        }
    }
}

