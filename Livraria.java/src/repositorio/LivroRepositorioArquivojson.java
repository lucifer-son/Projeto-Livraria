package repositorio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import excecoes.EntidadeNaoEncontradaExcecao;
import model.Livro;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LivroRepositorioArquivojson {

    private static LivroRepositorioArquivojson instance;

    private List<Livro> livros;
    private final File arquivo;
    private final Gson gson;

    private LivroRepositorioArquivojson() {
        String caminho = "dados/livros.json";

        this.arquivo = new File(caminho);
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        garantirPastaCriada();
        carregarArquivo();
    }

    public static LivroRepositorioArquivojson getInstance() {
        if (instance == null) {
            instance = new LivroRepositorioArquivojson();
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
                livros = new ArrayList<>();
                salvarArquivo(); // cria arquivo inicial
                return;
            }

            FileReader reader = new FileReader(arquivo);
            Type listType = new TypeToken<ArrayList<Livro>>() {}.getType();

            livros = gson.fromJson(reader, listType);
            reader.close();

            if (livros == null) {
                livros = new ArrayList<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
            livros = new ArrayList<>();
        }
    }


    private void salvarArquivo() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(livros, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void inserir(Livro livro) {
        livros.add(livro);
        salvarArquivo();
    }

    public void atualizar(Livro livroAtualizado) throws EntidadeNaoEncontradaExcecao {
        Livro existente = buscarPorId(livroAtualizado.getId());

        if (existente == null) {
            throw new EntidadeNaoEncontradaExcecao(
                    livroAtualizado.getId(),
                    "Livro não encontrado para atualização."
            );
        }

        int index = livros.indexOf(existente);
        livros.set(index, livroAtualizado);
        salvarArquivo();
    }

    public Livro buscarPorId(String id) {
        return livros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Livro> buscarTodos() {
        return new ArrayList<>(livros);
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        Livro livro = buscarPorId(id);

        if (livro == null) {
            throw new EntidadeNaoEncontradaExcecao(
                    id,
                    "Livro não encontrado para deleção."
            );
        }

        livros.remove(livro);
        salvarArquivo();
    }

    public List<Livro> buscarPorAutor(String autor) {
        List<Livro> filtrados = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getAutores() != null && livro.getAutores().contains(autor)) {
                filtrados.add(livro);
            }
        }
        return filtrados;
    }
}