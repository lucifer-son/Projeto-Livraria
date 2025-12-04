package GUI.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ItemPedido;
import model.Livro;
import model.Pedido;

public class PedidoDetalhesController {

    @FXML
    private Label pedidoIdLabel;
    @FXML
    private TableView<ItemPedido> itensTable;
    @FXML
    private TableColumn<ItemPedido, Livro> colLivro;
    @FXML
    private TableColumn<ItemPedido, Integer> colQuantidade;
    @FXML
    private TableColumn<ItemPedido, Double> colPrecoUnitario;
    @FXML
    private TableColumn<ItemPedido, Double> colSubtotal;

    private Stage dialogStage;

    public void initialize() {
        colLivro.setCellValueFactory(new PropertyValueFactory<>("livro"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colPrecoUnitario.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
        colSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        colLivro.setCellFactory(column -> new javafx.scene.control.TableCell<>() {
            @Override
            protected void updateItem(Livro item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getTitulo());
                }
            }
        });
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPedido(Pedido pedido) {
        pedidoIdLabel.setText("Detalhes do Pedido: " + pedido.getId());
        itensTable.setItems(FXCollections.observableArrayList(pedido.getItens()));
    }

    @FXML
    private void onVoltar() {
        dialogStage.close();
    }
}
