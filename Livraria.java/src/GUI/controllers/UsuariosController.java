package GUI.controllers;

import GUI.MainApp;
import cadastro.usuario.CadastroUsuario;
import excecoes.EntidadeNaoEncontradaExcecao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Usuario;

import java.io.IOException;
import java.util.List;

public class UsuariosController {

    @FXML
    private TableView<Usuario> usuariosTable;
    @FXML
    private TableColumn<Usuario, String> colUserId;
    @FXML
    private TableColumn<Usuario, String> colUserNome;
    @FXML
    private TableColumn<Usuario, String> colUserEmail;
    @FXML
    private TableColumn<Usuario, String> colUserRole;

    private CadastroUsuario cadastroUsuario;

    public void initialize() {
        cadastroUsuario = new CadastroUsuario();
        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUserNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        atualizarTabela();
    }

    private void atualizarTabela() {
        List<Usuario> usuarios = cadastroUsuario.buscarTodos();
        usuariosTable.setItems(FXCollections.observableArrayList(usuarios));
    }

    @FXML
    private void novoUsuario() {
        mostrarFormularioUsuario(null);
    }

    @FXML
    private void editarUsuario() {
        Usuario usuarioSelecionado = usuariosTable.getSelectionModel().getSelectedItem();
        if (usuarioSelecionado != null) {
            mostrarFormularioUsuario(usuarioSelecionado);
        } else {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um usuário para editar.");
        }
    }

    private void mostrarFormularioUsuario(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/GUI/resources/UsuarioForm.fxml"));
            VBox page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Formulário de Usuário");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(MainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            UsuarioFormController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUsuario(usuario, cadastroUsuario);

            dialogStage.showAndWait();
            atualizarTabela();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void excluirUsuario() {
        Usuario usuarioSelecionado = usuariosTable.getSelectionModel().getSelectedItem();
        if (usuarioSelecionado != null) {
            try {
                cadastroUsuario.remover(usuarioSelecionado.getId());
                atualizarTabela();
            } catch (EntidadeNaoEncontradaExcecao e) {
                showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um usuário para excluir.");
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
