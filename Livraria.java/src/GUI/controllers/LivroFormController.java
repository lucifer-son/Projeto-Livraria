package GUI.controllers;

import cadastro.produto.CadastroLivro;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;
import excecoes.EstoqueInvalidoExcecao;
import excecoes.PrecoInvalidoExcecao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Livro;

public class LivroFormController {

    @FXML
    private TextField tituloField;
    @FXML
    private TextField autorField;
    @FXML
    private TextField precoField;
    @FXML
    private TextField estoqueField;
    @FXML
    private TextArea detalhesArea;

    private Stage dialogStage;
    private Livro livro;
    private CadastroLivro cadastroLivro;
    private boolean novoLivro = true;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setLivro(Livro livro, CadastroLivro cadastroLivro) {
        this.livro = livro;
        this.cadastroLivro = cadastroLivro;
        if (livro != null) {
            novoLivro = false;
            tituloField.setText(livro.getTitulo());
            autorField.setText(livro.getAutor());
            precoField.setText(String.valueOf(livro.getPreco()));
            estoqueField.setText(String.valueOf(livro.getEstoque()));
            detalhesArea.setText(livro.getDetalhes());
        }
    }

    @FXML
    private void onSalvar() {
        String titulo = tituloField.getText();
        String autor = autorField.getText();
        String precoStr = precoField.getText();
        String estoqueStr = estoqueField.getText();
        String detalhes = detalhesArea.getText();

        if (titulo.isEmpty() || autor.isEmpty() || precoStr.isEmpty() || estoqueStr.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Preencha todos os campos.");
            return;
        }

        double preco;
        int estoque;
        try {
            preco = Double.parseDouble(precoStr);
            estoque = Integer.parseInt(estoqueStr);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Preço e estoque devem ser números.");
            return;
        }

        try {
            if (novoLivro) {
                livro = new Livro(titulo, autor, preco, estoque, detalhes);
                try {
                    cadastroLivro.cadastrar(livro);
                } catch (EntidadeJaExistenteExcecao e) {
                    showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
                    return;
                }
            } else {
                livro.setTitulo(titulo);
                livro.setAutor(autor);
                livro.setPreco(preco);
                livro.setEstoque(estoque);
                livro.setDetalhes(detalhes);
                try {
                    cadastroLivro.atualizar(livro);
                } catch (EntidadeNaoEncontradaExcecao e) {
                    showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
                    return;
                }
            }
            dialogStage.close();
        } catch (PrecoInvalidoExcecao | EstoqueInvalidoExcecao e) {
            showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
        }
    }

    @FXML
    private void onCancelar() {
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
