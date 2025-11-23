package cadastro.usuario;

import model.Usuario;
import repositorio.UsuarioRepositorioArray;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.util.List;


public class CadastroUsuario {

    private UsuarioRepositorioArray repositorio;

    public CadastroUsuario() {
        this.repositorio = UsuarioRepositorioArray.getInstance();
    }

    public void cadastrar(Usuario usuario) throws EntidadeJaExistenteExcecao {
        
        if (repositorio.buscarPorId(usuario.getId()) != null) {
            throw new EntidadeJaExistenteExcecao(usuario.getId(), "Já existe um usuário com o ID " + usuario.getId());
        }
        repositorio.inserir(usuario);
    }

    public void atualizar(Usuario usuario) throws EntidadeNaoEncontradaExcecao {
      
        if (repositorio.buscarPorId(usuario.getId()) == null) {
            throw new EntidadeNaoEncontradaExcecao(usuario.getId(), "Usuário não encontrado para atualização.");
        }
        repositorio.atualizar(usuario);
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        
        if (repositorio.buscarPorId(id) == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "Usuário não encontrado para remoção.");
        }
        repositorio.deletarPorId(id);
    }
    
    public Usuario buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Usuario> buscarTodos() {
        return repositorio.buscarTodos();
    }
    
    public Usuario buscarPorLogin(String login) {
        return repositorio.buscarPorLogin(login);
    }

    public void listar() {
        for (Usuario usuario : repositorio.buscarTodos()) {
            System.out.println(usuario);
        }
    }
}

