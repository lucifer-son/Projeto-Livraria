package GUI.controllers;

import GUI.MainApp;
import cadastro.usuario.CadastroCliente;
import excecoes.EmailInvalidoExcecao;
import excecoes.EntidadeJaExistenteExcecao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Cliente;
import model.Endereco;

public class CadastroClienteController {

    @FXML
    private TextField nomeField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private TextField telefoneField;
    @FXML
    private TextField cepField;
    @FXML
    private TextField ruaField;
    @FXML
    private TextField numeroField;
    @FXML
    private TextField bairroField;
    @FXML
    private TextField cidadeField;
    @FXML
    private TextField estadoField;
    @FXML
    private Label messageLabel;

    private final CadastroCliente cadastroCliente = new CadastroCliente();

    @FXML
    private void onCadastrarCliente() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String senha = senhaField.getText();
        String telefone = telefoneField.getText();
        String cep = cepField.getText();
        String rua = ruaField.getText();
        String numero = numeroField.getText();
        String bairro = bairroField.getText();
        String cidade = cidadeField.getText();
        String estado = estadoField.getText();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() ||
            cep.isEmpty() || rua.isEmpty() || numero.isEmpty() ||
            bairro.isEmpty() || cidade.isEmpty() || estado.isEmpty()) {
            messageLabel.setText("Por favor, preencha todos os campos obrigatórios.");
            return;
        }

        try {

            Endereco endereco = new Endereco(rua, numero, cidade, cep);
            endereco.setEstado(estado);
            endereco.setComplemento(bairro);

            Cliente novoCliente = new Cliente(nome, email, senha);
            
            if (!telefone.isEmpty()) {
                novoCliente.getTelefones().add(telefone);
            }
            novoCliente.getEnderecos().add(endereco);

            cadastroCliente.cadastrar(novoCliente);
            messageLabel.setText("Cliente cadastrado com sucesso!");
            messageLabel.setStyle("-fx-text-fill: green;");

            clearFields();
            MainApp.showScreen("login");

        } catch (EmailInvalidoExcecao e) {
            messageLabel.setText("Erro: " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
        } catch (EntidadeJaExistenteExcecao e) {
            messageLabel.setText("Erro: " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            messageLabel.setText("Erro inesperado ao cadastrar cliente: " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    @FXML
    private void onVoltar() {
        MainApp.showScreen("login");
    }

    private void clearFields() {
        nomeField.clear();
        emailField.clear();
        senhaField.clear();
        telefoneField.clear();
        cepField.clear();
        ruaField.clear();
        numeroField.clear();
        bairroField.clear();
        cidadeField.clear();
        estadoField.clear();
    }
}
