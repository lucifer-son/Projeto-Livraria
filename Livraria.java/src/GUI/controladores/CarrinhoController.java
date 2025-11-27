package GUI.controladores;

import GUI.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CarrinhoController extends BaseController {

    @FXML private TableView<?> carrinhoTable;
    @FXML private ListView<String> wishlistView;

    @FXML public void initialize() {

    }

    @FXML public void removerItem() {}
    @FXML public void moverParaWishlist() {}
    @FXML public void wishlistToCart() {}
    @FXML public void finalizarCompra() { navigate("pagamento"); }

    @FXML public void goBack() { navigate("menu"); }
}
