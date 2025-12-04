package GUI.controllers;

import cadastro.produto.CadastroDevolucao;
import excecoes.EntidadeJaExistenteExcecao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Devolucao;
import model.ItemPedido;
import model.Pedido;

public class DevolucaoController {

    @FXML
    private Label pedidoIdLabel;
    @FXML
    private ListView<ItemPedido> itensListView;
    @FXML
    private TextArea motivoArea;

    private Stage dialogStage;
    private Pedido pedido;
    private CadastroDevolucao cadastroDevolucao;

    public void initialize() {
        cadastroDevolucao = new CadastroDevolucao();
        itensListView.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(ItemPedido item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getLivro().getTitulo() + " (x" + item.getQuantidade() + ")");
                }
            }
        });
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        pedidoIdLabel.setText("Devolução do Pedido: " + pedido.getId());
        itensListView.getItems().setAll(pedido.getItens());
    }

    @FXML
    private void onConfirmarDevolucao() {
        String motivo = motivoArea.getText();
        if (motivo.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "O motivo da devolução não pode estar vazio.");
            return;
        }

        Devolucao devolucao = new Devolucao(pedido, motivo);
        try {
            cadastroDevolucao.cadastrar(devolucao);
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Devolução registrada com sucesso.");
            dialogStage.close();
        } catch (EntidadeJaExistenteExcecao e) {
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
