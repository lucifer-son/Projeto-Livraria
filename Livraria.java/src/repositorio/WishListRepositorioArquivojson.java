package repositorio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import excecoes.EntidadeNaoEncontradaExcecao;
import model.WishList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WishListRepositorioArquivojson {

    private static WishListRepositorioArquivojson instance;

    private List<WishList> wishLists;
    private final File arquivo;
    private final Gson gson;

    private WishListRepositorioArquivojson() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.arquivo = new File("dados/wishlists.json");

        garantirPasta();
        this.wishLists = carregarDoArquivo();
    }

    public static WishListRepositorioArquivojson getInstance() {
        if (instance == null) {
            instance = new WishListRepositorioArquivojson();
        }
        return instance;
    }

    private void garantirPasta() {
        File pasta = arquivo.getParentFile();
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    private List<WishList> carregarDoArquivo() {
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(arquivo)) {
            Type listaTipo = new TypeToken<List<WishList>>() {}.getType();
            List<WishList> lista = gson.fromJson(reader, listaTipo);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarNoArquivo() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(wishLists, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void inserir(WishList wishList) {
        wishLists.add(wishList);
        salvarNoArquivo();
    }

    public void atualizar(WishList wishList) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < wishLists.size(); i++) {
            if (wishLists.get(i).getId().equals(wishList.getId())) {
                wishLists.set(i, wishList);
                salvarNoArquivo();
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(wishList.getId(), "WishList não encontrada para atualização.");
    }

    public WishList buscarPorId(String id) {
        return wishLists.stream()
                .filter(w -> w.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<WishList> buscarTodos() {
        return new ArrayList<>(wishLists);
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        boolean removido = wishLists.removeIf(w -> w.getId().equals(id));

        if (!removido) {
            throw new EntidadeNaoEncontradaExcecao(id, "WishList não encontrada para deleção.");
        }

        salvarNoArquivo();
    }
}