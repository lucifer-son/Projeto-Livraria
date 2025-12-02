package repositorio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import excecoes.EntidadeNaoEncontradaExcecao;
import model.Avaliacao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoRepositorioArquivojson {

    private static AvaliacaoRepositorioArquivojson instance;

    private List<Avaliacao> avaliacoes;
    private final File arquivo;
    private final Gson gson;

    private AvaliacaoRepositorioArquivojson() {
        String caminho = "dados/avaliacoes.json";

        this.arquivo = new File(caminho);
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        garantirPastaCriada();
        carregarArquivo();
    }

    public static AvaliacaoRepositorioArquivojson getInstance() {
        if (instance == null) {
            instance = new AvaliacaoRepositorioArquivojson();
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
                avaliacoes = new ArrayList<>();
                salvarArquivo(); // cria o arquivo vazio
                return;
            }

            FileReader reader = new FileReader(arquivo);
            Type listType = new TypeToken<ArrayList<Avaliacao>>() {
            }.getType();

            avaliacoes = gson.fromJson(reader, listType);
            reader.close();

            if (avaliacoes == null) {
                avaliacoes = new ArrayList<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
            avaliacoes = new ArrayList<>();
        }
    }


    private void salvarArquivo() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(avaliacoes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void inserir(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
        salvarArquivo();
    }

    public void atualizar(Avaliacao avaliacaoAtualizada) throws EntidadeNaoEncontradaExcecao {
        Avaliacao existente = buscarPorId(avaliacaoAtualizada.getId());

        if (existente == null) {
            throw new EntidadeNaoEncontradaExcecao(
                    avaliacaoAtualizada.getId(),
                    "Avaliação não encontrada para atualização."
            );
        }

        int index = avaliacoes.indexOf(existente);
        avaliacoes.set(index, avaliacaoAtualizada);
        salvarArquivo();
    }

    public Avaliacao buscarPorId(String id) {
        return avaliacoes.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Avaliacao> buscarTodos() {
        return new ArrayList<>(avaliacoes);
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        Avaliacao avaliacao = buscarPorId(id);

        if (avaliacao == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "Avaliação não encontrada para deleção.");
        }

        avaliacoes.remove(avaliacao);
        salvarArquivo();
    }
}