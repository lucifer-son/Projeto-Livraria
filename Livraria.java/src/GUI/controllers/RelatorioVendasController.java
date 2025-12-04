package GUI.controllers;

import GUI.MainApp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Fachada;
import model.Pedido;
import model.Usuario;

import java.util.Date;
import java.util.List;

public class RelatorioVendasController {

    @FXML
    private TableView<Pedido> tabelaVendas;
    @FXML
    private TableColumn<Pedido, String> colId;
    @FXML
    private TableColumn<Pedido, String> colCliente;
    @FXML
    private TableColumn<Pedido, Date> colData;
    @FXML
    private TableColumn<Pedido, Double> colValor;
    @FXML
    private TableColumn<Pedido, Pedido.StatusPedido> colStatus;

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        carregarDadosVendas();
    }

    private void carregarDadosVendas() {
        List<Pedido> pedidos = Fachada.getInstance().buscarTodosPedidos();
        tabelaVendas.setItems(FXCollections.observableArrayList(pedidos));
    }

    @FXML
    private void handleVoltar() {
        MainApp.showScreen("menu");
    }
}
