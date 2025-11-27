package GUI.controladores;

import GUI.BaseController;
import javafx.fxml.FXML;

public class MenuController extends BaseController {

    @FXML public void openLivros() { navigate("livros"); }
    @FXML public void openClientes() { navigate("clientes"); }
    @FXML public void openUsuarios() { navigate("usuarios"); }
    @FXML public void openCarrinho() { navigate("carrinho"); }
    @FXML public void openPagamento() { navigate("pagamento"); }
    @FXML public void openPedidos() { navigate("pedidos"); }
    @FXML public void onLogout() { navigate("login"); }
}
