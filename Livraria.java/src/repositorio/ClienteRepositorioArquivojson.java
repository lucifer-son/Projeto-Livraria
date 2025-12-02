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

        if (!pasta.exists()) {
            boolean criada = pasta.mkdirs();
            if (criada) {
                System.out.println("Pasta criada: " + pasta.getAbsolutePath());
            }
        }
    }


    private void carregarArquivo() {
        try {
            if (!arquivo.exists()) {
                clientes = new ArrayList<>();
                salvarArquivo();
                return;
            }

            FileReader reader = new FileReader(arquivo);
            Type listType = new TypeToken<ArrayList<Cliente>>() {}.getType();

            clientes = gson.fromJson(reader, listType);
            reader.close();

            if (clientes == null) {
                clientes = new ArrayList<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
            clientes = new ArrayList<>();
        }
    }


    private void salvarArquivo() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(clientes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void inserir(Cliente cliente) {
        clientes.add(cliente);
        salvarArquivo();
    }

    public void atualizar(Cliente clienteAtualizado) throws EntidadeNaoEncontradaExcecao {
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
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Cliente> buscarTodos() {
        return new ArrayList<>(clientes);
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
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
        return clientes.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}