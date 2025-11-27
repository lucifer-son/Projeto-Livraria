package GUI.controladores;

import GUI.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PedidoController extends BaseController {

    @FXML private TableView<?> pedidosTable;

    @FXML public void initialize() {
        // carregar pedidos via PedidoService
    }

    @FXML public void verDetalhes() {
        // modal com itens do pedido
    }

    @FXML public void registrarDevolucao() {
        // formulário/ação para registrar devolução -> DevolucaoService
    }

    @FXML public void atualizarStatus() {
        // alterar status do pedido (enviado/entregue/cancelado)
    }

    @FXML public void goBack() { navigate("menu"); }
}
