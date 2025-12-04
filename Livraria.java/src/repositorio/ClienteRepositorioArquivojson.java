package repositorio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import excecoes.EntidadeNaoEncontradaExcecao;
import model.Cliente;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorioArquivojson {

    private static ClienteRepositorioArquivojson instance;

    private List<Cliente> clientes;
    private final File arquivo;
    private final Gson gson;

    private ClienteRepositorioArquivojson() {
        String caminho = "dados/clientes.json";

        this.arquivo = new File(caminho);
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        garantirPastaCriada();
        carregarArquivo();
    }

    public static ClienteRepositorioArquivojson getInstance() {
        if (instance == null) {
            instance = new ClienteRepositorioArquivojson();
        }
        return instance;
    }


    private void garantirPastaCriada() {
        File pasta = arquivo.getParentFile();
        System.out.println("DEBUG: ClienteRepositorio - Tentando garantir pasta: " + pasta.getAbsolutePath());
        if (!pasta.exists()) {
            boolean criada = pasta.mkdirs();
            if (criada) {
                System.out.println("DEBUG: ClienteRepositorio - Pasta criada com sucesso: " + pasta.getAbsolutePath());
            } else {
                System.err.println("DEBUG: ClienteRepositorio - Falha ao criar pasta: " + pasta.getAbsolutePath());
            }
        }
    }


    private void carregarArquivo() {
        System.out.println("DEBUG: ClienteRepositorio - Carregando clientes do arquivo: " + arquivo.getAbsolutePath());
        if (!arquivo.exists()) {
            System.out.println("DEBUG: ClienteRepositorio - Arquivo de clientes não existe. Retornando lista vazia.");
            clientes = new ArrayList<>();
            salvarArquivo(); // cria arquivo inicial vazio
            return;
        }

        try (FileReader reader = new FileReader(arquivo)) {
            Type listType = new TypeToken<ArrayList<Cliente>>() {}.getType();

            clientes = gson.fromJson(reader, listType);
            System.out.println("DEBUG: ClienteRepositorio - Clientes carregados: " + (clientes != null ? clientes.size() : 0));
            reader.close();

            if (clientes == null) {
                clientes = new ArrayList<>();
            }

        } catch (IOException e) {
            System.err.println("DEBUG: ClienteRepositorio - Erro ao carregar clientes do arquivo: " + e.getMessage());
            e.printStackTrace();
            clientes = new ArrayList<>();
        }
    }


    private void salvarArquivo() {
        System.out.println("DEBUG: ClienteRepositorio - Salvando " + clientes.size() + " clientes no arquivo: " + arquivo.getAbsolutePath());
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(clientes, writer);
            System.out.println("DEBUG: ClienteRepositorio - Clientes salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("DEBUG: ClienteRepositorio - Erro ao salvar clientes no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public void inserir(Cliente cliente) {
        System.out.println("DEBUG: ClienteRepositorio - Inserindo cliente: " + cliente.getEmail());
        clientes.add(cliente);
        salvarArquivo();
    }

    public void atualizar(Cliente clienteAtualizado) throws EntidadeNaoEncontradaExcecao {
        System.out.println("DEBUG: ClienteRepositorio - Atualizando cliente: " + clienteAtualizado.getEmail());
        Cliente existente = buscarPorId(clienteAtualizado.getId());

        if (existente == null) {
            throw new EntidadeNaoEncontradaExcecao(
                    clienteAtualizado.getId(),
                    "Cliente não encontrado para atualização."
            );
        }

        int index = clientes.indexOf(existente);
        clientes.set(index, clienteAtualizado);
        salvarArquivo();
    }

    public Cliente buscarPorId(String id) {
        System.out.println("DEBUG: ClienteRepositorio - Buscando cliente por ID: " + id);
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Cliente> buscarTodos() {
        System.out.println("DEBUG: ClienteRepositorio - Buscando todos os clientes.");
        return new ArrayList<>(clientes);
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        System.out.println("DEBUG: ClienteRepositorio - Deletando cliente por ID: " + id);
        Cliente cliente = buscarPorId(id);

        if (cliente == null) {
            throw new EntidadeNaoEncontradaExcecao(
                    id,
                    "Cliente não encontrado para deleção."
            );
        }

        clientes.remove(cliente);
        salvarArquivo();
    }

    public Cliente buscarPorEmail(String email) {
        System.out.println("DEBUG: ClienteRepositorio - Buscando cliente por e-mail: " + email);
        return clientes.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}
