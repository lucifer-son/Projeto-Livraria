package GUI.controllers;

import cadastro.usuario.CadastroUsuario;
import excecoes.EmailInvalidoExcecao;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.TipoUsuario;
import model.Usuario;

public class UsuarioFormController {

    @FXML
    private TextField nomeField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField senhaField;


    private Stage dialogStage;
    private Usuario usuario;
    private CadastroUsuario cadastroUsuario;
    private boolean novoUsuario = true;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setUsuario(Usuario usuario, CadastroUsuario cadastroUsuario) {
        this.usuario = usuario;
        this.cadastroUsuario = cadastroUsuario;
        if (usuario != null) {
            novoUsuario = false;
            nomeField.setText(usuario.getNome());
            emailField.setText(usuario.getEmail());
            senhaField.setText(usuario.getSenha());
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
            if (novoUsuario) {
                usuario = new Usuario(nome, email, senha);
                try {
                    cadastroUsuario.cadastrar(usuario);
                } catch (EntidadeJaExistenteExcecao e) {
                    showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
                    return;
                }
            } else {
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha(senha);
                try {
                    cadastroUsuario.atualizar(usuario);
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
