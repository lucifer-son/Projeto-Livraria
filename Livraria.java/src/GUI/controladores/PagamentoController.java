package GUI.controladores;

import GUI.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PagamentoController extends BaseController {

    @FXML private TextArea resumoArea;
    @FXML private ChoiceBox<String> metodoChoice;
    @FXML private TextField cartaoField;

    @FXML public void initialize() {
        metodoChoice.getItems().addAll("Cartão de Crédito", "Boleto", "PIX");
    }

    @FXML public void onPagar() {
        String metodo = metodoChoice.getValue();
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Pagamento realizado (placeholder).", ButtonType.OK);
        a.showAndWait();
        navigate("pedidos");
    }

    @FXML public void goBack() { navigate("menu"); }
}
