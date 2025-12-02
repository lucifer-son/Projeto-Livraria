package repositorio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import excecoes.EntidadeNaoEncontradaExcecao;
import model.Pagamento;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PagamentoRepositorioArquivojson {

    private static PagamentoRepositorioArquivojson instance;

    private List<Pagamento> pagamentos;
    private final File arquivo;
    private final Gson gson;

    private PagamentoRepositorioArquivojson() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.arquivo = new File("dados/pagamentos.json");

        garantirPasta();
        this.pagamentos = carregarDoArquivo();
    }

    public static PagamentoRepositorioArquivojson getInstance() {
        if (instance == null) {
            instance = new PagamentoRepositorioArquivojson();
        }
        return instance;
    }

    private void garantirPasta() {
        File pasta = arquivo.getParentFile();
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    private List<Pagamento> carregarDoArquivo() {
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(arquivo)) {
            Type listaTipo = new TypeToken<List<Pagamento>>() {}.getType();
            List<Pagamento> lista = gson.fromJson(reader, listaTipo);
            return (lista != null) ? lista : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarNoArquivo() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(pagamentos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void inserir(Pagamento pagamento) {
        pagamentos.add(pagamento);
        salvarNoArquivo();
    }

    public void atualizar(Pagamento pagamento) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < pagamentos.size(); i++) {
            if (pagamentos.get(i).getId().equals(pagamento.getId())) {
                pagamentos.set(i, pagamento);
                salvarNoArquivo();
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(pagamento.getId(), "Pagamento não encontrado para atualização.");
    }

    public Pagamento buscarPorId(String id) {
        return pagamentos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Pagamento> buscarTodos() {
        return new ArrayList<>(pagamentos);
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        boolean removido = pagamentos.removeIf(p -> p.getId().equals(id));

        if (!removido) {
            throw new EntidadeNaoEncontradaExcecao(id, "Pagamento não encontrado para deletar.");
        }

        salvarNoArquivo();
    }
}

