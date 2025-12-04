package GUI.controllers;

import GUI.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.Fachada;
import model.TipoUsuario;
import model.Usuario;

public class MenuController {

    @FXML private Button btnMeusLivros;
    @FXML private Button btnCarrinho;
    @FXML private Button btnAvaliacoes;

    @FXML private Button btnGerenciarEstoque;
    @FXML private Button btnGerenciarUsuarios;
    @FXML private Button btnGerenciarPedidos;
    @FXML private Button btnRelatorioVendas;

    @FXML
    public void initialize() {
        Usuario usuarioLogado = Fachada.getInstance().getUsuarioLogado();

        if (usuarioLogado != null && usuarioLogado.getTipo() == TipoUsuario.CLIENTE) {

            btnGerenciarEstoque.setVisible(false);
            btnGerenciarEstoque.setManaged(false);
            btnGerenciarUsuarios.setVisible(false);
            btnGerenciarUsuarios.setManaged(false);
            btnGerenciarPedidos.setVisible(false);
            btnGerenciarPedidos.setManaged(false);
            btnRelatorioVendas.setVisible(false);
            btnRelatorioVendas.setManaged(false);
        }

    }

    @FXML
    private void onLivros() {
        MainApp.showScreen("livros");
    }

    @FXML
    private void onCarrinho() {
        MainApp.showScreen("carrinho");
    }

    @FXML
    private void onAvaliacoes() {

        System.out.println("Navegar para Minhas Avaliações");
    }

    @FXML
    private void onGerenciarEstoque() {
        MainApp.showScreen("livros");
    }

    @FXML
    private void onGerenciarUsuarios() {
        MainApp.showScreen("usuarios");
    }

    @FXML
    private void onGerenciarPedidos() {
        MainApp.showScreen("pedidos");
    }

    @FXML
    private void onRelatorioVendas() {
        MainApp.showScreen("relatorioVendas");
    }

    @FXML
    private void onLogout() {
        Fachada.getInstance().logout();
        MainApp.showScreen("login");
    }
}
