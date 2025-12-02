package repositorio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import excecoes.EntidadeNaoEncontradaExcecao;
import model.Pedido;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepositorioArquivojson {

    private static PedidoRepositorioArquivojson instance;

    private List<Pedido> pedidos;
    private File arquivo;
    private Gson gson;

    private PedidoRepositorioArquivojson() {
        String caminho = "dados/pedidos.json";

        this.arquivo = new File(caminho);
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        garantirPastaCriada();
        carregarArquivo();
    }

    public static PedidoRepositorioArquivojson getInstance() {
        if (instance == null) {
            instance = new PedidoRepositorioArquivojson();
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
                pedidos = new ArrayList<>();
                salvarArquivo();
                return;
            }

            FileReader reader = new FileReader(arquivo);
            Type listType = new TypeToken<ArrayList<Pedido>>() {}.getType();

            pedidos = gson.fromJson(reader, listType);
            reader.close();

            if (pedidos == null) {
                pedidos = new ArrayList<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
            pedidos = new ArrayList<>();
        }
    }


    private void salvarArquivo() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(pedidos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void inserir(Pedido pedido) {
        pedidos.add(pedido);
        salvarArquivo();
    }

    public void atualizar(Pedido pedidoAtualizado) throws EntidadeNaoEncontradaExcecao {
        Pedido existente = buscarPorId(pedidoAtualizado.getId());

        if (existente == null) {
            throw new EntidadeNaoEncontradaExcecao(
                    pedidoAtualizado.getId(),
                    "Pedido não encontrado para atualização."
            );
        }

        int index = pedidos.indexOf(existente);
        pedidos.set(index, pedidoAtualizado);
        salvarArquivo();
    }

    public Pedido buscarPorId(String id) {
        return pedidos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Pedido> buscarTodos() {
        return new ArrayList<>(pedidos);
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        Pedido pedido = buscarPorId(id);

        if (pedido == null) {
            throw new EntidadeNaoEncontradaExcecao(
                    id,
                    "Pedido não encontrado para deleção."
            );
        }

        pedidos.remove(pedido);
        salvarArquivo();
    }

    public List<Pedido> buscarPorStatus(Pedido.StatusPedido status) {
        List<Pedido> filtrados = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getStatus() == status) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }
}