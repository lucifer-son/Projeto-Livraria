package GUI.controladores;

import GUI.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends BaseController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    public void onLogin() {
        String email = emailField.getText();
        String senha = passwordField.getText();

        // TODO: integrar com UsuarioService para validação
        boolean ok = !"".equals(email) && !"".equals(senha); // placeholder

        if (ok) {
            navigate("menu");
        } else {
            errorLabel.setText("Credenciais inválidas.");
        }
    }

    @FXML
    public void onExit() {

        Stage stage = (Stage) errorLabel.getScene().getWindow();
        stage.close();
    }
}
