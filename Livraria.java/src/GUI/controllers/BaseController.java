package GUI.controllers;

import javafx.scene.control.Alert;

public abstract class BaseController {

    /**
     * Exibe um diálogo de alerta para o usuário.
     *
     * @param type    O tipo de alerta (ex: INFORMATION, WARNING, ERROR).
     * @param title   O título da janela de alerta.
     * @param message A mensagem a ser exibida.
     */
    protected void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
