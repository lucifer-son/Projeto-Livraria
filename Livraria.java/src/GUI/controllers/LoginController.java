package GUI.controllers;

import GUI.MainApp;
import main.Fachada;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void onLogin() {
        String email = emailField.getText();
        String senha = passwordField.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            errorLabel.setText("Preencha todos os campos.");
            return;
        }

        if (Fachada.getInstance().autenticar(email, senha)) {
            MainApp.showScreen("menu");
        } else {
            errorLabel.setText("E-mail ou senha inválidos.");
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
