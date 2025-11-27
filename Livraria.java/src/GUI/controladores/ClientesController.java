package GUI.controladores;

import model.Cliente; // ajuste conforme seu pacote model
import GUI.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ClientesController extends BaseController {

    @FXML private TableView<Cliente> clientesTable;
    @FXML private TableColumn<Cliente, Integer> colCliId;
    @FXML private TableColumn<Cliente, String> colCliNome;
    @FXML private TableColumn<Cliente, String> colCliEmail;

    @FXML private TextField ruaField, numeroField, cidadeField, cepField;

    @FXML
    public void initialize() {
        // configurar colunas e carregar clientes via ClienteService
    }

    @FXML public void novoCliente() {
        // abrir formulário modal para criar cliente (placeholder)
    }
    @FXML public void editarCliente() {}
    @FXML public void excluirCliente() {}
    @FXML public void salvarEndereco() {
        // TODO: integrar com EnderecoEntrega e ClienteService
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Endereço salvo (placeholder).", ButtonType.OK);
        a.showAndWait();
    }

    @FXML public void goBack() { navigate("menu"); }
}
