package GUI.controllers;

import GUI.MainApp;
import main.Fachada;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView logoImageView;

    @FXML
    public void initialize() {
        // A imagem era para ser definida aqui via código,
        // mas no projeto original isso ficou em branco.
        // (Pode deixar exatamente assim para voltar ao estado inicial.)
    }

    @FXML
    private void onLogin() {
        String email = emailField.getText();
        String senha = passwordField.getText();

        if (email.isEmpty() || senha.isEmpty()) {

            System.out.println("Preencha todos os campos.");
            return;
        }

        if (Fachada.getInstance().autenticar(email, senha)) {
            MainApp.showScreen("menu");
        } else {

            System.out.println("E-mail ou senha inválidos.");
        }
    }

    @FXML
    private void onExit() {
        MainApp.getPrimaryStage().close();
    }

    @FXML
    private void onCreateNewUser() {
        MainApp.showScreen("cadastroCliente");
    }
}
