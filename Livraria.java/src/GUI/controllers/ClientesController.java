package GUI.controllers;

import GUI.MainApp;
import cadastro.usuario.CadastroCliente;
import excecoes.EntidadeNaoEncontradaExcecao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;
import model.Endereco;

import java.io.IOException;
import java.util.List;

public class ClientesController {

    @FXML
    private TableView<Cliente> clientesTable;
    @FXML
    private TableColumn<Cliente, String> colCliId;
    @FXML
    private TableColumn<Cliente, String> colCliNome;
    @FXML
    private TableColumn<Cliente, String> colCliEmail;

    @FXML
    private TextField ruaField;
    @FXML
    private TextField numeroField;
    @FXML
    private TextField cidadeField;
    @FXML
    private TextField cepField;

    private CadastroCliente cadastroCliente;

    public void initialize() {
        cadastroCliente = new CadastroCliente();
        colCliId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCliEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        clientesTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> exibirDetalhesCliente(newValue));

        atualizarTabela();
    }

    private void atualizarTabela() {
        List<Cliente> clientes = cadastroCliente.buscarTodos();
        clientesTable.setItems(FXCollections.observableArrayList(clientes));
    }

    private void exibirDetalhesCliente(Cliente cliente) {
        if (cliente != null && cliente.getEndereco() != null) {
            Endereco endereco = cliente.getEndereco();
            ruaField.setText(endereco.getRua());
            numeroField.setText(endereco.getNumero());
            cidadeField.setText(endereco.getCidade());
            cepField.setText(endereco.getCep());
        } else {
            limparCamposEndereco();
        }
    }

    @FXML
    private void novoCliente() {
        mostrarFormularioCliente(null);
    }

    @FXML
    private void editarCliente() {
        Cliente clienteSelecionado = clientesTable.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            mostrarFormularioCliente(clienteSelecionado);
        } else {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um cliente para editar.");
        }
    }

    private void mostrarFormularioCliente(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/GUI/resources/ClienteForm.fxml"));
            VBox page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Formulário de Cliente");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(MainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ClienteFormController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCliente(cliente, cadastroCliente);

            dialogStage.showAndWait();
            atualizarTabela();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void excluirCliente() {
        Cliente clienteSelecionado = clientesTable.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            try {
                cadastroCliente.remover(clienteSelecionado.getId());
                atualizarTabela();
            } catch (EntidadeNaoEncontradaExcecao e) {
                showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um cliente para excluir.");
        }
    }

    @FXML
    private void salvarEndereco() {
        Cliente clienteSelecionado = clientesTable.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            String rua = ruaField.getText();
            String numero = numeroField.getText();
            String cidade = cidadeField.getText();
            String cep = cepField.getText();

            if (rua.isEmpty() || numero.isEmpty() || cidade.isEmpty() || cep.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Erro", "Preencha todos os campos de endereço.");
                return;
            }

            Endereco endereco = new Endereco(rua, numero, cidade, cep);
            clienteSelecionado.setEndereco(endereco);

            try {
                cadastroCliente.atualizar(clienteSelecionado);
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Endereço salvo com sucesso.");
            } catch (EntidadeNaoEncontradaExcecao e) {
                showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um cliente para salvar o endereço.");
        }
    }

    @FXML
    private void goBack() {
        MainApp.showScreen("menu");
    }

    private void limparCamposEndereco() {
        ruaField.clear();
        numeroField.clear();
        cidadeField.clear();
        cepField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
