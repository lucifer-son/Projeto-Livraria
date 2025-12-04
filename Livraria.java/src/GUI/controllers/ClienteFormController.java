package GUI.controllers;

import cadastro.usuario.CadastroCliente;
import excecoes.EmailInvalidoExcecao;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;

public class ClienteFormController {

    @FXML
    private TextField nomeField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField senhaField;

    private Stage dialogStage;
    private Cliente cliente;
    private CadastroCliente cadastroCliente;
    private boolean novoCliente = true;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCliente(Cliente cliente, CadastroCliente cadastroCliente) {
        this.cliente = cliente;
        this.cadastroCliente = cadastroCliente;
        if (cliente != null) {
            novoCliente = false;
            nomeField.setText(cliente.getNome());
            emailField.setText(cliente.getEmail());
            senhaField.setText(cliente.getSenha());
        }
    }

    @FXML
    private void onSalvar() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String senha = senhaField.getText();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Preencha todos os campos.");
            return;
        }

        try {
            if (novoCliente) {
                cliente = new Cliente(nome, email, senha);
                try {
                    cadastroCliente.cadastrar(cliente);
                } catch (EntidadeJaExistenteExcecao e) {
                    showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
                    return;
                }
            } else {
                cliente.setNome(nome);
                cliente.setEmail(email);
                cliente.setSenha(senha);
                try {
                    cadastroCliente.atualizar(cliente);
                } catch (EntidadeNaoEncontradaExcecao e) {
                    showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
                    return;
                }
            }
            dialogStage.close();
        } catch (EmailInvalidoExcecao e) {
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
