package GUI.controllers;

import GUI.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import main.Fachada;
import model.Livro;
import model.Pedido;
import model.TipoUsuario;
import model.Usuario;
import model.ItemPedido;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.PrecoInvalidoExcecao;
import excecoes.QuantidadeInvalidaExcecao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PagamentoController {

    @FXML
    private Label totalPagarLabel;
    @FXML
    private ComboBox<String> metodoPagamentoComboBox;
    @FXML
    private TextField numeroCartaoField;
    @FXML
    private TextField nomeCartaoField;
    @FXML
    private TextField validadeCartaoField;
    @FXML
    private TextField cvvCartaoField;
    @FXML
    private ImageView barcodeImageView;
    @FXML
    private ImageView qrCodeImageView;

    private double valorTotal;

    public void initialize() {
        metodoPagamentoComboBox.getItems().addAll("Cartão de Crédito", "Boleto", "Pix");
        metodoPagamentoComboBox.setValue("Cartão de Crédito");

        updatePaymentMethodView("Cartão de Crédito");

        metodoPagamentoComboBox.valueProperty().addListener((obs, oldVal, newVal) ->
            updatePaymentMethodView(newVal)
        );

        calcularValorTotal();
    }

    private void updatePaymentMethodView(String metodo) {
        boolean isCartao = "Cartão de Crédito".equals(metodo);
        boolean isBoleto = "Boleto".equals(metodo);
        boolean isPix = "Pix".equals(metodo);

        setCamposCartaoVisiveis(isCartao);
        barcodeImageView.setVisible(isBoleto);
        barcodeImageView.setManaged(isBoleto);
        qrCodeImageView.setVisible(isPix);
        qrCodeImageView.setManaged(isPix);
    }

    private void calcularValorTotal() {
        valorTotal = Fachada.getInstance().getValorTotalCarrinho();
        totalPagarLabel.setText(String.format("Total a Pagar: R$ %.2f", valorTotal));
    }

    private void setCamposCartaoVisiveis(boolean visivel) {
        numeroCartaoField.setVisible(visivel);
        numeroCartaoField.setManaged(visivel);
        nomeCartaoField.setVisible(visivel);
        nomeCartaoField.setManaged(visivel);
        validadeCartaoField.setVisible(visivel);
        validadeCartaoField.setManaged(visivel);
        cvvCartaoField.setVisible(visivel);
        cvvCartaoField.setManaged(visivel);
    }

    @FXML
    private void handleFinalizarPagamento() {
        if (Fachada.getInstance().getItensDoCarrinho().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Seu carrinho está vazio. Não é possível finalizar o pagamento.");
            return;
        }

        Usuario usuarioLogado = Fachada.getInstance().getUsuarioLogado();
        if (usuarioLogado == null || usuarioLogado.getTipo() != TipoUsuario.CLIENTE) {
            showAlert(Alert.AlertType.ERROR, "Erro de Permissão", "Apenas clientes podem finalizar pedidos.");
            return;
        }

        String metodoPagamento = metodoPagamentoComboBox.getValue();

        if (metodoPagamento.equals("Cartão de Crédito")) {
            if (numeroCartaoField.getText().isEmpty() || nomeCartaoField.getText().isEmpty() ||
                validadeCartaoField.getText().isEmpty() || cvvCartaoField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Erro de Validação", "Por favor, preencha todos os campos do cartão.");
                return;
            }
        }

        boolean pagamentoProcessado = simularProcessamentoPagamento(metodoPagamento);

        if (pagamentoProcessado) {
            Date dataPedido = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

            List<ItemPedido> itensPedido = new ArrayList<>();
            for (Map.Entry<Livro, Integer> entry : Fachada.getInstance().getItensDoCarrinho().entrySet()) {
                try {
                    itensPedido.add(new ItemPedido(entry.getKey(), entry.getValue()));
                } catch (QuantidadeInvalidaExcecao | PrecoInvalidoExcecao e) {
                    showAlert(Alert.AlertType.ERROR, "Erro no Item do Pedido", "Erro ao criar item do pedido: " + e.getMessage());
                    e.printStackTrace();
                    return;
                }
            }

            Pedido novoPedido = new Pedido.Builder(
                UUID.randomUUID().toString(),
                usuarioLogado.getId(),
                dataPedido
            )
            .withStatus(Pedido.StatusPedido.PROCESSANDO)
            .withValorTotal(valorTotal)
            .withMetodoPagamento(metodoPagamento)
            .withItens(itensPedido)
            .build();

            try {
                Fachada.getInstance().cadastrarPedido(novoPedido);
                Fachada.getInstance().limparCarrinho();
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Pedido realizado com sucesso! Redirecionando para o menu.");
                MainApp.showScreen("menu");
            } catch (EntidadeJaExistenteExcecao e) {
                showAlert(Alert.AlertType.ERROR, "Erro ao Salvar Pedido", "Ocorreu um erro ao registrar seu pedido: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro Inesperado", "Ocorreu um erro inesperado ao finalizar o pedido.");
            }

        } else {
            showAlert(Alert.AlertType.ERROR, "Falha no Pagamento", "Não foi possível processar seu pagamento. Tente novamente.");
        }
    }

    private boolean simularProcessamentoPagamento(String metodo) {
        System.out.println("Simulando pagamento via: " + metodo);
        return true;
    }

    @FXML
    private void handleVoltarCarrinho() {
        MainApp.showScreen("carrinho");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
