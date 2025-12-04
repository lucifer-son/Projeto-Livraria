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
        System.out.println("DEBUG: Tentando garantir pasta: " + pasta.getAbsolutePath());
        if (!pasta.exists()) {
            boolean criada = pasta.mkdirs();
            if (criada) {
                System.out.println("DEBUG: Pasta criada com sucesso: " + pasta.getAbsolutePath());
            } else {
                System.err.println("DEBUG: Falha ao criar pasta: " + pasta.getAbsolutePath());
            }
        }
    }

    private List<Usuario> carregarDoArquivo() {
        System.out.println("DEBUG: Carregando usuários do arquivo: " + arquivo.getAbsolutePath());
        if (!arquivo.exists()) {
            System.out.println("DEBUG: Arquivo de usuários não existe. Retornando lista vazia.");
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(arquivo)) {
            Type listaTipo = new TypeToken<List<Usuario>>() {}.getType();
            List<Usuario> lista = gson.fromJson(reader, listaTipo);
            System.out.println("DEBUG: Usuários carregados: " + (lista != null ? lista.size() : 0));
            return (lista != null) ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("DEBUG: Erro ao carregar usuários do arquivo: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarNoArquivo() {
        System.out.println("DEBUG: Salvando " + usuarios.size() + " usuários no arquivo: " + arquivo.getAbsolutePath());
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(usuarios, writer);
            System.out.println("DEBUG: Usuários salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("DEBUG: Erro ao salvar usuários no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public void inserir(Usuario usuario) {
        System.out.println("DEBUG: Inserindo usuário: " + usuario.getEmail());
        usuarios.add(usuario);
        salvarNoArquivo();
    }

    public void atualizar(Usuario usuario) throws EntidadeNaoEncontradaExcecao {
        System.out.println("DEBUG: Atualizando usuário: " + usuario.getEmail());
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
        System.out.println("DEBUG: Buscando usuário por ID: " + id);
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> buscarTodos() {
        System.out.println("DEBUG: Buscando todos os usuários.");
        return new ArrayList<>(usuarios);
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        System.out.println("DEBUG: Deletando usuário por ID: " + id);
        boolean removido = usuarios.removeIf(u -> u.getId().equals(id));

        if (!removido) {
            throw new EntidadeNaoEncontradaExcecao(id, "Usuário não encontrado para deleção.");
        }

        salvarNoArquivo();
    }


    public Usuario buscarPorLogin(String login) {
        System.out.println("DEBUG: Buscando usuário por login (email): " + login);
        return usuarios.stream()
                .filter(u -> u.getLogin().equalsIgnoreCase(login))
                .findFirst()
                .orElse(null);
    }
}
