package GUI.controllers;

import main.Fachada;
import cadastro.produto.CadastroAvaliacao;
import excecoes.EntidadeJaExistenteExcecao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Avaliacao;
import model.Cliente;
import model.Livro;
import model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class AvaliacoesController {

    @FXML
    private Label livroTituloLabel;
    @FXML
    private ListView<Avaliacao> avaliacoesListView;
    @FXML
    private TextField comentarioField;
    @FXML
    private Spinner<Integer> notaSpinner;

    private Stage dialogStage;
    private Livro livro;
    private CadastroAvaliacao cadastroAvaliacao;

    public void initialize() {
        cadastroAvaliacao = new CadastroAvaliacao();
        avaliacoesListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Avaliacao item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("Nota: %d - %s", item.getNota(), item.getComentario()));
                }
            }
        });
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
        livroTituloLabel.setText("Avaliações para: " + livro.getTitulo());
        atualizarLista();
    }

    private void atualizarLista() {
        List<Avaliacao> todasAvaliacoes = cadastroAvaliacao.buscarTodos();
        List<Avaliacao> avaliacoesDoLivro = todasAvaliacoes.stream()
                .filter(a -> a.getLivro().getId().equals(livro.getId()))
                .collect(Collectors.toList());
        avaliacoesListView.setItems(FXCollections.observableArrayList(avaliacoesDoLivro));
    }

    @FXML
    private void onAdicionarAvaliacao() {
        String comentario = comentarioField.getText();
        int nota = notaSpinner.getValue();
        Usuario usuarioLogado = Fachada.getInstance().getUsuarioLogado();

        if (comentario.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "O comentário não pode estar vazio.");
            return;
        }

        if (usuarioLogado == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Você precisa estar logado para avaliar um livro.");
            return;
        }
        
        if (!(usuarioLogado instanceof Cliente)) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Apenas clientes podem avaliar livros.");
            return;
        }
        Cliente cliente = (Cliente) usuarioLogado;


        Avaliacao avaliacao = new Avaliacao(livro, cliente, nota, comentario);
        try {
            cadastroAvaliacao.cadastrar(avaliacao);
            atualizarLista();
            comentarioField.clear();
        } catch (EntidadeJaExistenteExcecao e) {
            showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
        }
    }

    @FXML
    private void onVoltar() {
        dialogStage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
