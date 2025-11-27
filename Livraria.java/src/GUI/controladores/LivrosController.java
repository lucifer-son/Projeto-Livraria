package GUI.controladores;


import model.Livro; 
import GUI.BaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LivrosController extends BaseController {

    @FXML private TableView<Livro> livrosTable;
    @FXML private TableColumn<Livro, Integer> colId;
    @FXML private TableColumn<Livro, String> colTitulo;
    @FXML private TableColumn<Livro, String> colAutor;
    @FXML private TableColumn<Livro, Double> colPreco;
    @FXML private TableColumn<Livro, Integer> colEstoque;
    @FXML private TextArea detalheArea;

    private ObservableList<Livro> livros = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
       

        colTitulo.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getTitulo()));
        colPreco.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getPreco()));
        colEstoque.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getEstoque()));

        livrosTable.setItems(livros);

       

        livrosTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) detalheArea.setText(buildDetalhes(newV));
        });
    }

    private String buildDetalhes(Livro l) {
        return String.format("Título: %s\nPreço: %.2f\nEstoque: %d",
                l.getTitulo(), l.getPreco(), l.getEstoque());
    }

    @FXML public void addToCart() {
        Livro sel = livrosTable.getSelectionModel().getSelectedItem();
        if (sel != null) {
            
            System.out.println("Adicionado ao carrinho: " + sel.getTitulo());
        }
    }

    @FXML public void verAvaliacoes() {
        Livro sel = livrosTable.getSelectionModel().getSelectedItem();
        if (sel != null) {
            
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Mostrando avaliações (placeholder) para: " + sel.getTitulo(), ButtonType.OK);
            a.setHeaderText("Avaliações");
            a.showAndWait();
        }
    }

    @FXML public void goBack() { navigate("menu"); }
}

