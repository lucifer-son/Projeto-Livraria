package cadastro.usuario;

import model.Usuario;
import repositorio.UsuarioRepositorioArquivojson;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.util.List;


public class CadastroUsuario {

    private UsuarioRepositorioArquivojson repositorio;

    public CadastroUsuario() {
        this.repositorio = UsuarioRepositorioArquivojson.getInstance();
    }

    public void cadastrar(Usuario usuario) throws EntidadeJaExistenteExcecao {
        System.out.println("DEBUG: CadastroUsuario.cadastrar() - Tentando cadastrar usuário: " + usuario.getEmail());
        if (repositorio.buscarPorId(usuario.getId()) != null) {
            System.out.println("DEBUG: CadastroUsuario.cadastrar() - Usuário com ID " + usuario.getId() + " já existe.");
            throw new EntidadeJaExistenteExcecao(usuario.getId(), "Já existe um usuário com o ID " + usuario.getId());
        }
        if (repositorio.buscarPorLogin(usuario.getEmail()) != null) {
            System.out.println("DEBUG: CadastroUsuario.cadastrar() - Usuário com email " + usuario.getEmail() + " já existe.");
            throw new EntidadeJaExistenteExcecao(usuario.getEmail(), "Já existe um usuário com o e-mail " + usuario.getEmail());
        }
        repositorio.inserir(usuario);
        System.out.println("DEBUG: CadastroUsuario.cadastrar() - Usuário " + usuario.getEmail() + " inserido no repositório.");
    }

    public void atualizar(Usuario usuario) throws EntidadeNaoEncontradaExcecao {
        System.out.println("DEBUG: CadastroUsuario.atualizar() - Tentando atualizar usuário: " + usuario.getEmail());
        if (repositorio.buscarPorId(usuario.getId()) == null) {
            System.out.println("DEBUG: CadastroUsuario.atualizar() - Usuário com ID " + usuario.getId() + " não encontrado para atualização.");
            throw new EntidadeNaoEncontradaExcecao(usuario.getId(), "Usuário não encontrado para atualização.");
        }
        repositorio.atualizar(usuario);
        System.out.println("DEBUG: CadastroUsuario.atualizar() - Usuário " + usuario.getEmail() + " atualizado no repositório.");
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        System.out.println("DEBUG: CadastroUsuario.remover() - Tentando remover usuário com ID: " + id);
        if (repositorio.buscarPorId(id) == null) {
            System.out.println("DEBUG: CadastroUsuario.remover() - Usuário com ID " + id + " não encontrado para remoção.");
            throw new EntidadeNaoEncontradaExcecao(id, "Usuário não encontrado para remoção.");
        }
        repositorio.deletarPorId(id);
        System.out.println("DEBUG: CadastroUsuario.remover() - Usuário com ID " + id + " removido do repositório.");
    }

    public Usuario buscarPorId(String id) {
        System.out.println("DEBUG: CadastroUsuario.buscarPorId() - Buscando usuário por ID: " + id);
        return repositorio.buscarPorId(id);
    }
    public List<Usuario> buscarTodos() {
        System.out.println("DEBUG: CadastroUsuario.buscarTodos() - Buscando todos os usuários.");
        return repositorio.buscarTodos();
    }
    public Usuario buscarPorLogin(String login) {
        System.out.println("DEBUG: CadastroUsuario.buscarPorLogin() - Buscando usuário por login (email): " + login);
        return repositorio.buscarPorLogin(login);
    }
    public void listar() {
        System.out.println("DEBUG: CadastroUsuario.listar() - Listando usuários.");
        for (Usuario usuario : repositorio.buscarTodos()) {
            System.out.println(usuario);
        }
    }
}
