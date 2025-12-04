package repositorio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Carrinho;
import model.Livro;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class CarrinhoRepositorioArquivojson {

    private static CarrinhoRepositorioArquivojson instance;
    private final File arquivo;
    private final Gson gson;

    private CarrinhoRepositorioArquivojson() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.arquivo = new File("dados/carrinho.json");
        garantirPasta();
        carregar();
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

    private void carregar() {
        if (!arquivo.exists()) {
            return;
        }

        try (FileReader reader = new FileReader(arquivo)) {

            Type mapType = new TypeToken<Map<Livro, Integer>>() {}.getType();
            Map<Livro, Integer> itensDoArquivo = gson.fromJson(reader, mapType);

            if (itensDoArquivo != null) {
                Carrinho carrinho = Carrinho.getInstance();
                carrinho.limparCarrinho();
                for (Map.Entry<Livro, Integer> entry : itensDoArquivo.entrySet()) {
                    carrinho.adicionarItem(entry.getKey(), entry.getValue());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo do carrinho: " + e.getMessage());
        }
    }

    public void salvar(Carrinho carrinho) {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(carrinho.getItens(), writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo do carrinho: " + e.getMessage());
        }
    }
}
