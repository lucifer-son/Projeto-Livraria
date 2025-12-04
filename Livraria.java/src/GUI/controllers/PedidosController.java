package GUI.controllers;

import GUI.MainApp;
import cadastro.CadastroPedido;
import excecoes.EntidadeNaoEncontradaExcecao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;
import model.Pedido;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PedidosController {

    @FXML
    private TableView<Pedido> pedidosTable;
    @FXML
    private TableColumn<Pedido, String> colPedidoId;
    @FXML
    private TableColumn<Pedido, Cliente> colCliente;
    @FXML
    private TableColumn<Pedido, Date> colData;
    @FXML
    private TableColumn<Pedido, Double> colValor;
    @FXML
    private TableColumn<Pedido, Pedido.StatusPedido> colStatus;

    private CadastroPedido cadastroPedido;

    public void initialize() {
        cadastroPedido = new CadastroPedido();
        colPedidoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        colCliente.setCellFactory(column -> new javafx.scene.control.TableCell<>() {
            @Override
            protected void updateItem(Cliente item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getNome());
                }
            }
        });

        atualizarTabela();
    }

    private void atualizarTabela() {
        List<Pedido> pedidos = cadastroPedido.buscarTodos();
        pedidosTable.setItems(FXCollections.observableArrayList(pedidos));
    }

    @FXML
    private void verDetalhes() {
        Pedido pedidoSelecionado = pedidosTable.getSelectionModel().getSelectedItem();
        if (pedidoSelecionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/GUI/resources/PedidoDetalhes.fxml"));
                VBox page = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Detalhes do Pedido");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(MainApp.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                PedidoDetalhesController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setPedido(pedidoSelecionado);

                dialogStage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um pedido para ver os detalhes.");
        }
    }

    @FXML
    private void registrarDevolucao() {
        Pedido pedidoSelecionado = pedidosTable.getSelectionModel().getSelectedItem();
        if (pedidoSelecionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/GUI/resources/Devolucao.fxml"));
                VBox page = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Registrar Devolução");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(MainApp.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                DevolucaoController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setPedido(pedidoSelecionado);

                dialogStage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um pedido para registrar a devolução.");
        }
    }

    @FXML
    private void atualizarStatus() {
        Pedido pedidoSelecionado = pedidosTable.getSelectionModel().getSelectedItem();
        if (pedidoSelecionado != null) {
            List<Pedido.StatusPedido> statusDisponiveis = Arrays.asList(Pedido.StatusPedido.values());
            ChoiceDialog<Pedido.StatusPedido> dialog = new ChoiceDialog<>(pedidoSelecionado.getStatus(), statusDisponiveis);
            dialog.setTitle("Atualizar Status do Pedido");
            dialog.setHeaderText("Selecione o novo status para o pedido " + pedidoSelecionado.getId());
            dialog.setContentText("Status:");

            Optional<Pedido.StatusPedido> result = dialog.showAndWait();
            result.ifPresent(novoStatus -> {
                pedidoSelecionado.setStatus(novoStatus);
                try {
                    cadastroPedido.atualizar(pedidoSelecionado);
                    atualizarTabela();
                } catch (EntidadeNaoEncontradaExcecao e) {
                    showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
                }
            });
        } else {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um pedido para atualizar o status.");
        }
    }

    @FXML
    private void goBack() {
        MainApp.showScreen("menu");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
