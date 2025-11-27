package GUI.controladores;

import model.Usuario;
import GUI.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UsuarioController extends BaseController {

    @FXML private TableView<Usuario> usuariosTable;
    @FXML private TableColumn<Usuario, String> colUserId;
    @FXML private TableColumn<Usuario, String> colUserNome;
    @FXML private TableColumn<Usuario, String> colUserEmail;
    @FXML private TableColumn<Usuario, String> colUserRole;


    @FXML public void initialize() {
    }

    @FXML public void novoUsuario() {}
    @FXML public void editarUsuario() {}
    @FXML public void excluirUsuario() {}

    @FXML public void goBack() { navigate("menu"); }
}
