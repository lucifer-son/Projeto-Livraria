package repositorio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import excecoes.EntidadeNaoEncontradaExcecao;
import model.Usuario;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorioArquivojson {

    private static UsuarioRepositorioArquivojson instance;

    private List<Usuario> usuarios;
    private final File arquivo;
    private final Gson gson;

    private UsuarioRepositorioArquivojson() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.arquivo = new File("dados/usuarios.json");

        garantirPasta();
        this.usuarios = carregarDoArquivo();
    }

    public static UsuarioRepositorioArquivojson getInstance() {
        if (instance == null) {
            instance = new UsuarioRepositorioArquivojson();
        }
        return instance;
    }

    private void garantirPasta() {
        File pasta = arquivo.getParentFile();
        if (!pasta.exists()) {
            pasta.mkdirs();  // Cria "dados/"
        }
    }

    private List<Usuario> carregarDoArquivo() {
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(arquivo)) {
            Type listaTipo = new TypeToken<List<Usuario>>() {}.getType();
            List<Usuario> lista = gson.fromJson(reader, listaTipo);
            return (lista != null) ? lista : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarNoArquivo() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void inserir(Usuario usuario) {
        usuarios.add(usuario);
        salvarNoArquivo();
    }

    public void atualizar(Usuario usuario) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(usuario.getId())) {
                usuarios.set(i, usuario);
                salvarNoArquivo();
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(usuario.getId(), "Usuário não encontrado para atualização.");
    }

    public Usuario buscarPorId(String id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> buscarTodos() {
        return new ArrayList<>(usuarios);
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        boolean removido = usuarios.removeIf(u -> u.getId().equals(id));

        if (!removido) {
            throw new EntidadeNaoEncontradaExcecao(id, "Usuário não encontrado para deleção.");
        }

        salvarNoArquivo();
    }


    public Usuario buscarPorLogin(String login) {
        return usuarios.stream()
                .filter(u -> u.getLogin().equalsIgnoreCase(login))
                .findFirst()
                .orElse(null);
    }
}