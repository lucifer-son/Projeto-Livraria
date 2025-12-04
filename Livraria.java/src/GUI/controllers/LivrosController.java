package GUI.controllers;

import GUI.MainApp;
import cadastro.produto.CadastroLivro;
import excecoes.EntidadeNaoEncontradaExcecao;
import excecoes.OperacaoNaoPermitidaExcecao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Livro;
import main.Fachada;
import model.TipoUsuario;
import model.Usuario;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class LivrosController {

    @FXML
    private TableView<Livro> livrosTable;
    @FXML
    private TableColumn<Livro, String> colId;
    @FXML
    private TableColumn<Livro, String> colTitulo;
    @FXML
    private TableColumn<Livro, String> colAutor;
    @FXML
    private TableColumn<Livro, Double> colPreco;
    @FXML
    private TableColumn<Livro, String> colEstoque;

    @FXML
    private TextArea detalheArea;

    @FXML
    private Button btnNovoLivro;
    @FXML
    private Button btnEditarLivro;
    @FXML
    private Button btnExcluirLivro;
    @FXML
    private Button btnMeuCarrinho;

    private CadastroLivro cadastroLivro;

    public void initialize() {
        cadastroLivro = new CadastroLivro();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        livrosTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> exibirDetalhesLivro(newValue));

        Usuario usuarioLogado = Fachada.getInstance().getUsuarioLogado();
        if (usuarioLogado != null && usuarioLogado.getTipo() == TipoUsuario.CLIENTE) {
            btnNovoLivro.setVisible(false);
            btnNovoLivro.setManaged(false);
            btnEditarLivro.setVisible(false);
            btnEditarLivro.setManaged(false);
            btnExcluirLivro.setVisible(false);
            btnExcluirLivro.setManaged(false);
            btnMeuCarrinho.setVisible(true);
            btnMeuCarrinho.setManaged(true);

            colEstoque.setCellValueFactory(cellData -> {
                int estoque = cellData.getValue().getEstoque();
                String status = estoque > 0 ? "Em Estoque" : "Fora de Estoque";
                return new javafx.beans.property.SimpleStringProperty(status);
            });
        } else {
            btnMeuCarrinho.setVisible(false);
            btnMeuCarrinho.setManaged(false);
            colEstoque.setCellValueFactory(cellData -> {
                int estoque = cellData.getValue().getEstoque();
                return new javafx.beans.property.SimpleStringProperty(String.valueOf(estoque));
            });
        }

        atualizarTabela();
    }

    private void atualizarTabela() {
        List<Livro> livros = cadastroLivro.buscarTodos();
        livrosTable.setItems(FXCollections.observableArrayList(livros));
    }

    private void exibirDetalhesLivro(Livro livro) {
        if (livro != null) {
            StringBuilder detalhes = new StringBuilder();
            detalhes.append("Título: ").append(livro.getTitulo()).append("\n");
            detalhes.append("Autor(es): ").append(String.join(", ", livro.getAutores())).append("\n");
            detalhes.append("Editora: ").append(livro.getEditora()).append("\n");
            detalhes.append("ISBN: ").append(livro.getIsbn()).append("\n");
            detalhes.append("Ano: ").append(livro.getAnoPublicacao()).append("\n");
            detalhes.append("Preço: R$ ").append(String.format("%.2f", livro.getPreco())).append("\n");
            detalhes.append("Categoria: ").append(livro.getCategoria()).append("\n");
            detalhes.append("Páginas: ").append(livro.getNumPaginas()).append("\n");
            detalhes.append("Descrição: ").append(livro.getDescricao()).append("\n");

            Usuario usuarioLogado = Fachada.getInstance().getUsuarioLogado();
            if (usuarioLogado != null && usuarioLogado.getTipo() == TipoUsuario.CLIENTE) {
                detalhes.append("Disponibilidade: ").append(livro.getEstoque() > 0 ? "Em Estoque" : "Fora de Estoque").append("\n");
            } else {
                detalhes.append("Estoque: ").append(livro.getEstoque()).append("\n");
            }

            detalheArea.setText(detalhes.toString());
        } else {
            detalheArea.clear();
        }
    }

    @FXML
    private void novoLivro() {
        try {
            Usuario usuarioLogado = Fachada.getInstance().getUsuarioLogado();
            if (usuarioLogado == null || usuarioLogado.getTipo() == TipoUsuario.CLIENTE) {
                throw new OperacaoNaoPermitidaExcecao("Livro", "cadastro", "Usuário não autorizado");
            }
            mostrarFormularioLivro(null);
        } catch (OperacaoNaoPermitidaExcecao e) {
            showAlert(Alert.AlertType.ERROR, "Acesso Negado", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro ao preparar o formulário de novo livro.");
        }
    }

    @FXML
    private void editarLivro() {
        Livro livroSelecionado = livrosTable.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            try {
                Usuario usuarioLogado = Fachada.getInstance().getUsuarioLogado();
                if (usuarioLogado == null || usuarioLogado.getTipo() == TipoUsuario.CLIENTE) {
                    throw new OperacaoNaoPermitidaExcecao(livroSelecionado.getId(), "edição", "Usuário não autorizado");
                }
                mostrarFormularioLivro(livroSelecionado);
            } catch (OperacaoNaoPermitidaExcecao e) {
                showAlert(Alert.AlertType.ERROR, "Acesso Negado", e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro ao preparar o formulário de edição de livro.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um livro para editar.");
        }
    }

    private void mostrarFormularioLivro(Livro livro) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/GUI/resources/LivroForm.fxml"));
            VBox page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Formulário de Livro");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(MainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            LivroFormController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLivro(livro, cadastroLivro);

            dialogStage.showAndWait();
            atualizarTabela();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void excluirLivro() {
        Livro livroSelecionado = livrosTable.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            try {
                Usuario usuarioLogado = Fachada.getInstance().getUsuarioLogado();
                if (usuarioLogado == null || usuarioLogado.getTipo() == TipoUsuario.CLIENTE) {
                    throw new OperacaoNaoPermitidaExcecao(livroSelecionado.getId(), "exclusão", "Usuário não autorizado");
                }
                Fachada.getInstance().removerLivro(livroSelecionado.getId());
                atualizarTabela();
            } catch (OperacaoNaoPermitidaExcecao e) {
                showAlert(Alert.AlertType.ERROR, "Acesso Negado", e.getMessage());
            } catch (EntidadeNaoEncontradaExcecao e) {
                showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um livro para excluir.");
        }
    }

    @FXML
    private void addToCart() {
        Livro livroSelecionado = livrosTable.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            Usuario usuarioLogado = Fachada.getInstance().getUsuarioLogado();
            if (usuarioLogado == null || usuarioLogado.getTipo() != TipoUsuario.CLIENTE) {
                showAlert(Alert.AlertType.ERROR, "Acesso Negado", "Apenas clientes podem adicionar livros ao carrinho.");
                return;
            }

            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Adicionar ao Carrinho");
            dialog.setHeaderText("Adicionando " + livroSelecionado.getTitulo() + " ao carrinho.");
            dialog.setContentText("Quantidade:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(quantidadeStr -> {
                try {
                    int quantidade = Integer.parseInt(quantidadeStr);
                    if (quantidade > 0 && quantidade <= livroSelecionado.getEstoque()) {
                        Fachada.getInstance().adicionarItemAoCarrinho(livroSelecionado, quantidade);
                        showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Livro adicionado ao carrinho.");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Erro", "Quantidade inválida ou estoque insuficiente.");
                    }
                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.ERROR, "Erro", "Quantidade inválida.");
                }
            });
        } else {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um livro para adicionar ao carrinho.");
        }
    }

    @FXML
    private void verAvaliacoes() {
        Livro livroSelecionado = livrosTable.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/GUI/resources/Avaliacoes.fxml"));
                VBox page = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Avaliações");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(MainApp.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                AvaliacoesController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setLivro(livroSelecionado);

                dialogStage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um livro para ver as avaliações.");
        }
    }

    @FXML
    private void goBack() {
        MainApp.showScreen("menu");
    }

    @FXML
    private void handleMeuCarrinho() {
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
