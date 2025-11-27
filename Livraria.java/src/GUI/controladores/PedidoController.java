package GUI.controladores;

import GUI.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PedidoController extends BaseController {

    @FXML private TableView<?> pedidosTable;

    @FXML public void initialize() {    
    }

    @FXML public void verDetalhes() {
    }

    @FXML public void registrarDevolucao() {
    }

    @FXML public void atualizarStatus() {
    }

    @FXML public void goBack() { navigate("menu"); }
}
