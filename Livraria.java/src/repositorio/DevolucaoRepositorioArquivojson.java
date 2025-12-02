package repositorio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import excecoes.EntidadeNaoEncontradaExcecao;
import model.Devolucao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DevolucaoRepositorioArquivojson {

    private static DevolucaoRepositorioArquivojson instance;

    private List<Devolucao> devolucoes;
    private final File arquivo;
    private final Gson gson;

    private DevolucaoRepositorioArquivojson() {
        String caminho = "dados/devolucoes.json";

        this.arquivo = new File(caminho);
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        garantirPastaCriada();
        carregarArquivo();
    }

    public static DevolucaoRepositorioArquivojson getInstance() {
        if (instance == null) {
            instance = new DevolucaoRepositorioArquivojson();
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
                devolucoes = new ArrayList<>();
                salvarArquivo(); // cria arquivo inicial
                return;
            }

            FileReader reader = new FileReader(arquivo);
            Type listType = new TypeToken<ArrayList<Devolucao>>() {}.getType();

            devolucoes = gson.fromJson(reader, listType);
            reader.close();

            if (devolucoes == null) {
                devolucoes = new ArrayList<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
            devolucoes = new ArrayList<>();
        }
    }


    private void salvarArquivo() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(devolucoes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void inserir(Devolucao devolucao) {
        devolucoes.add(devolucao);
        salvarArquivo();
    }

    public void atualizar(Devolucao devolucaoAtualizada) throws EntidadeNaoEncontradaExcecao {
        Devolucao existente = buscarPorId(devolucaoAtualizada.getId());

        if (existente == null) {
            throw new EntidadeNaoEncontradaExcecao(
                    devolucaoAtualizada.getId(),
                    "Devolução não encontrada para atualização."
            );
        }

        int index = devolucoes.indexOf(existente);
        devolucoes.set(index, devolucaoAtualizada);
        salvarArquivo();
    }

    public Devolucao buscarPorId(String id) {
        return devolucoes.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Devolucao> buscarTodos() {
        return new ArrayList<>(devolucoes);
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        Devolucao devolucao = buscarPorId(id);

        if (devolucao == null) {
            throw new EntidadeNaoEncontradaExcecao(
                    id,
                    "Devolução não encontrada para deleção."
            );
        }

        devolucoes.remove(devolucao);
        salvarArquivo();
    }
}

