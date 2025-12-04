package GUI.controllers;

import GUI.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Livro;
import main.Fachada;

import java.util.Map;

public class CarrinhoController {

    @FXML
    private TableView<CarrinhoItem> carrinhoTable;
    @FXML
    private TableColumn<CarrinhoItem, String> colTitulo;
    @FXML
    private TableColumn<CarrinhoItem, Integer> colQuantidade;
    @FXML
    private TableColumn<CarrinhoItem, Double> colPrecoUnitario;
    @FXML
    private TableColumn<CarrinhoItem, Double> colSubtotal;
    @FXML
    private Label totalLabel;

    private ObservableList<CarrinhoItem> itensCarrinho;

    public void initialize() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colPrecoUnitario.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
        colSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        itensCarrinho = FXCollections.observableArrayList();
        carrinhoTable.setItems(itensCarrinho);

        atualizarCarrinhoView();
    }

    public void atualizarCarrinhoView() {
        itensCarrinho.clear();
        double total = 0.0;
        for (Map.Entry<Livro, Integer> entry : Fachada.getInstance().getItensDoCarrinho().entrySet()) {
            Livro livro = entry.getKey();
            Integer quantidade = entry.getValue();
            itensCarrinho.add(new CarrinhoItem(livro, quantidade));
            total += livro.getPreco() * quantidade;
        }
        totalLabel.setText(String.format("Total: R$ %.2f", total));
    }

    @FXML
    private void handleRemoverItem() {
        CarrinhoItem itemSelecionado = carrinhoTable.getSelectionModel().getSelectedItem();
        if (itemSelecionado != null) {
            Fachada.getInstance().removerItemDoCarrinho(itemSelecionado.getLivro());
            atualizarCarrinhoView();
        } else {
            showAlert(Alert.AlertType.WARNING, "Nenhum Item Selecionado", "Por favor, selecione um item para remover.");
        }
    }

    @FXML
    private void handleLimparCarrinho() {
        Fachada.getInstance().limparCarrinho();
        atualizarCarrinhoView();
    }

    @FXML
    private void handleContinuarComprando() {
        MainApp.showScreen("livros");
    }

    @FXML
    private void handleFinalizarPedido() {
        if (Fachada.getInstance().getItensDoCarrinho().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Carrinho Vazio", "Seu carrinho está vazio. Adicione itens antes de finalizar o pedido.");
            return;
        }
        MainApp.showScreen("pagamento");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class CarrinhoItem {
        private Livro livro;
        private String titulo;
        private int quantidade;
        private double precoUnitario;
        private double subtotal;

        public CarrinhoItem(Livro livro, int quantidade) {
            this.livro = livro;
            this.titulo = livro.getTitulo();
            this.quantidade = quantidade;
            this.precoUnitario = livro.getPreco();
            this.subtotal = livro.getPreco() * quantidade;
        }

        public Livro getLivro() {
            return livro;
        }

        public String getTitulo() {
            return titulo;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public double getPrecoUnitario() {
            return precoUnitario;
        }

        public double getSubtotal() {
            return subtotal;
        }
    }
}
