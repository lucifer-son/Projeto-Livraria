package repositorio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import excecoes.EntidadeNaoEncontradaExcecao;
import model.Carrinho;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoRepositorioArquivojson {

    private static CarrinhoRepositorioArquivojson instance;

    private List<Carrinho> carrinhos;
    private final File arquivo;
    private final Gson gson;

    private CarrinhoRepositorioArquivojson() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.arquivo = new File("dados/carrinhos.json");

        garantirPasta();
        this.carrinhos = carregarDoArquivo();
    }

    public static CarrinhoRepositorioArquivojson getInstance() {
        if (instance == null) {
            instance = new CarrinhoRepositorioArquivojson();
        }
        return instance;
    }

    private void garantirPasta() {
        File pasta = arquivo.getParentFile();
        if (!pasta.exists()) {
            pasta.mkdirs(); 
        }
    }

    private List<Carrinho> carregarDoArquivo() {
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(arquivo)) {
            Type listaTipo = new TypeToken<List<Carrinho>>() {}.getType();
            List<Carrinho> lista = gson.fromJson(reader, listaTipo);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarNoArquivo() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(carrinhos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void inserir(Carrinho carrinho) {
        carrinhos.add(carrinho);
        salvarNoArquivo();
    }

    public void atualizar(Carrinho carrinho) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < carrinhos.size(); i++) {
            if (carrinhos.get(i).getId().equals(carrinho.getId())) {
                carrinhos.set(i, carrinho);
                salvarNoArquivo();
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(carrinho.getId(), "Carrinho não encontrado para atualização.");
    }

    public Carrinho buscarPorId(String id) {
        return carrinhos.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Carrinho> buscarTodos() {
        return new ArrayList<>(carrinhos);
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        boolean removido = carrinhos.removeIf(c -> c.getId().equals(id));

        if (!removido) {
            throw new EntidadeNaoEncontradaExcecao(id, "Carrinho não encontrado para deleção.");
        }

        salvarNoArquivo();
    }
}