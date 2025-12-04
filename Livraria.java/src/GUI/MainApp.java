package GUI;

import cadastro.usuario.CadastroCliente;
import cadastro.usuario.CadastroUsuario;
import excecoes.EmailInvalidoExcecao;
import excecoes.EntidadeJaExistenteExcecao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Cliente;
import model.Usuario;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainApp extends Application {

    private static Stage primaryStage;
    private static final Map<String, String> routes = new HashMap<>();

    static {
        routes.put("login", "/GUI/resources/Login.fxml");
        routes.put("menu", "/GUI/resources/Menu.fxml");
        routes.put("livros", "/GUI/resources/Livros.fxml");
        routes.put("clientes", "/GUI/resources/Clientes.fxml");
        routes.put("usuarios", "/GUI/resources/Usuario.fxml");
        routes.put("pagamento", "/GUI/resources/Pagamento.fxml");
        routes.put("pedidos", "/GUI/resources/Pedido.fxml");
        routes.put("cadastroCliente", "/GUI/resources/CadastroCliente.fxml");
        routes.put("carrinho", "/GUI/resources/Carrinho.fxml");
        routes.put("pagamento", "/GUI/resources/Pagamento.fxml");
        routes.put("relatorioVendas", "/GUI/resources/RelatorioVendas.fxml");
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("Sistema Livraria");

        CadastroUsuario cadastroUsuario = new CadastroUsuario();
        CadastroCliente cadastroCliente = new CadastroCliente();


        try {
            Usuario adminUser = new Usuario("Rafael Gamelo", "rafael.gamelo@ufrpe.br", "admin123"); 
            cadastroUsuario.cadastrar(adminUser);
            System.out.println("INFO: Usuário admin 'rafael.gamelo@ufrpe.br' cadastrado com sucesso.");
        } catch (EntidadeJaExistenteExcecao e) {
            System.out.println("INFO: Usuário admin 'rafael.gamelo@ufrpe.br' já existe.");
        } catch (EmailInvalidoExcecao e) {
            System.err.println("ERRO: E-mail do admin inválido durante inicialização: " + e.getMessage());
        }

        if (cadastroCliente.buscarTodos().isEmpty()) {
            try {
                Cliente clientePadrao = new Cliente("Usuario Teste", "test@test.com", "123");
                cadastroCliente.cadastrar(clientePadrao);
                System.out.println("INFO: Usuário padrão 'test@test.com' criado com sucesso.");
            } catch (EntidadeJaExistenteExcecao | EmailInvalidoExcecao e) {
                System.err.println("ERRO: Erro ao criar usuário padrão: " + e.getMessage());
            }
        }


        showScreen("login");
        primaryStage.show();
    }

    public static void showScreen(String name) {
        try {
            String path = routes.get(name);
            if (path == null) throw new IllegalArgumentException("Rota não encontrada: " + name);
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(path));
            Parent root = loader.load();
            Scene scene = primaryStage.getScene();
            if (scene == null) {
                scene = new Scene(root, 900, 600);
                scene.getStylesheets().add(MainApp.class.getResource("/GUI/ccs/app.css").toExternalForm());
                primaryStage.setScene(scene);
            } else {
                scene.setRoot(root);
            }
            primaryStage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
