package GUI.controllers;

import GUI.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Fachada;
import model.Usuario;

public class FachadaController {

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private Label usuarioLogadoLabel;
    @FXML
    private Label tipoUsuarioLabel;

    public void initialize() {
        updateUserInfo();
    }

    @FXML
    private void handleLogin() {
        String login = loginField.getText();
        String senha = senhaField.getText();

        if (Fachada.getInstance().autenticar(login, senha)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Sucesso", "Bem-vindo, " + Fachada.getInstance().getUsuarioLogado().getNome() + "!");
            updateUserInfo();

            MainApp.showScreen("menu");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Falhou", "Login ou senha inválidos.");
        }
    }

    @FXML
    private void handleLogout() {
        Fachada.getInstance().logout();
        showAlert(Alert.AlertType.INFORMATION, "Logout Sucesso", "Você foi desconectado.");
        updateUserInfo();

        MainApp.showScreen("login");
    }

    private void updateUserInfo() {
        Usuario usuarioLogado = Fachada.getInstance().getUsuarioLogado();
        if (usuarioLogado != null) {
            usuarioLogadoLabel.setText("Usuário: " + usuarioLogado.getNome());
            tipoUsuarioLabel.setText("Tipo: " + usuarioLogado.getTipo().toString());
            loginField.clear();
            senhaField.clear();
            loginField.setDisable(true);
            senhaField.setDisable(true);
        } else {
            usuarioLogadoLabel.setText("Nenhum usuário logado");
            tipoUsuarioLabel.setText("");
            loginField.setDisable(false);
            senhaField.setDisable(false);
        }
    }

    @FXML
    private void goToLivros() {
        MainApp.showScreen("livros");
    }

    @FXML
    private void goToClientes() {

        showAlert(Alert.AlertType.INFORMATION, "Navegação", "Navegar para a tela de Clientes (ainda não implementado).");
    }

    @FXML
    private void goToPedidos() {

        showAlert(Alert.AlertType.INFORMATION, "Navegação", "Navegar para a tela de Pedidos (ainda não implementado).");
    }

    @FXML
    private void goToUsuarios() {

        showAlert(Alert.AlertType.INFORMATION, "Navegação", "Navegar para a tela de Usuários (ainda não implementado).");
    }

    @FXML
    private void goToAvaliacoes() {

        showAlert(Alert.AlertType.INFORMATION, "Navegação", "Navegar para a tela de Avaliações (ainda não implementado).");
    }

    @FXML
    private void goToRelatorios() {

        showAlert(Alert.AlertType.INFORMATION, "Navegação", "Navegar para a tela de Relatórios (ainda não implementado).");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
