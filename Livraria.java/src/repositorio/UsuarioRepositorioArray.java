package repositorio;

import excecoes.EntidadeNaoEncontradaExcecao;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;
// import java.util.Optional; // Removido

public class UsuarioRepositorioArray {

    private final Usuario[] usuarios;
    private int proximoIndice;
    private static final int TAMANHO_MAXIMO = 100;

    public UsuarioRepositorioArray() {
        this.usuarios = new Usuario[TAMANHO_MAXIMO];
        this.proximoIndice = 0;
    }

    public void inserir(Usuario usuario) {
        if (proximoIndice < TAMANHO_MAXIMO) {
            usuarios[proximoIndice] = usuario;
            proximoIndice++;
        } else {
            System.err.println("Erro: Repositório de usuários está cheio.");
        }
    }

    public void atualizar(Usuario usuario) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < proximoIndice; i++) {
            if (usuarios[i].getId().equals(usuario.getId())) {
                usuarios[i] = usuario;
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(usuario.getId(), "Usuário não encontrado para atualização.");
    }

    // Retorno alterado de Optional<Usuario> para Usuario
    public Usuario buscarPorId(String id) {
        for (int i = 0; i < proximoIndice; i++) {
            if (usuarios[i] != null && usuarios[i].getId().equals(id)) {
                return usuarios[i]; // Retorna o usuário diretamente
            }
        }
        return null; // Retorna null se não encontrar
    }

    public List<Usuario> buscarTodos() {
        List<Usuario> todos = new ArrayList<>();
        for (int i = 0; i < proximoIndice; i++) {
            todos.add(usuarios[i]);
        }
        return todos;
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximoIndice; i++) {
            if (usuarios[i].getId().equals(id)) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            throw new EntidadeNaoEncontradaExcecao(id, "Usuário não encontrado para deleção.");
        }

        for (int i = indiceParaRemover; i < proximoIndice - 1; i++) {
            usuarios[i] = usuarios[i + 1];
        }

        proximoIndice--;
        usuarios[proximoIndice] = null;
    }

    // Retorno alterado de Optional<Usuario> para Usuario
    public Usuario buscarPorLogin(String login) {
        for (int i = 0; i < proximoIndice; i++) {
            if (usuarios[i] != null && usuarios[i].getLogin().equalsIgnoreCase(login)) {
                return usuarios[i]; // Retorna o usuário diretamente
            }
        }
        return null; // Retorna null se não encontrar
    }
}
