package GUI;

import model.Cliente;

public class Sessao {

    private static Sessao instance;
    private Cliente clienteLogado;

    private Sessao() {}

    public static Sessao getInstance() {
        if (instance == null) {
            instance = new Sessao();
        }
        return instance;
    }

    public Cliente getClienteLogado() {
        return clienteLogado;
    }

    public void setClienteLogado(Cliente clienteLogado) {
        this.clienteLogado = clienteLogado;
    }

    public void limpar() {
        this.clienteLogado = null;
    }
}
