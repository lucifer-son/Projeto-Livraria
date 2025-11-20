package repositorio;

import excecoes.EntidadeNaoEncontradaExcecao;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorioArray {

    private Cliente[] clientes;
    private int proximoIndice;
    private static final int TAMANHO_MAXIMO = 100;

    public ClienteRepositorioArray() {
        this.clientes = new Cliente[TAMANHO_MAXIMO];
        this.proximoIndice = 0;
    }

    public void inserir(Cliente cliente) {
        if (proximoIndice < TAMANHO_MAXIMO) {
            clientes[proximoIndice] = cliente;
            proximoIndice++;
        } else {
            System.err.println("Erro: Repositório de clientes está cheio.");
        }
    }

    public void atualizar(Cliente cliente) throws EntidadeNaoEncontradaExcecao {
        for (int i = 0; i < proximoIndice; i++) {
            if (clientes[i].getId().equals(cliente.getId())) {
                clientes[i] = cliente;
                return;
            }
        }
        throw new EntidadeNaoEncontradaExcecao(cliente.getId(), "Cliente não encontrado para atualização.");
    }

  
    public Cliente buscarPorId(String id) {
        for (int i = 0; i < proximoIndice; i++) {
            if (clientes[i] != null && clientes[i].getId().equals(id)) {
                return clientes[i]; // Retorna o cliente diretamente
            }
        }
        return null; 
    }

    public List<Cliente> buscarTodos() {
        List<Cliente> todos = new ArrayList<>();
        for (int i = 0; i < proximoIndice; i++) {
            todos.add(clientes[i]);
        }
        return todos;
    }

    public void deletarPorId(String id) throws EntidadeNaoEncontradaExcecao {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximoIndice; i++) {
            if (clientes[i].getId().equals(id)) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            throw new EntidadeNaoEncontradaExcecao(id, "Cliente não encontrado para deleção.");
        }

        for (int i = indiceParaRemover; i < proximoIndice - 1; i++) {
            clientes[i] = clientes[i + 1];
        }

        proximoIndice--;
        clientes[proximoIndice] = null;
    }

  
    public Cliente buscarPorEmail(String email) {
        for (int i = 0; i < proximoIndice; i++) {
            if (clientes[i] != null && clientes[i].getEmail().equalsIgnoreCase(email)) {
                return clientes[i]; 
            }
        }
        return null; 
    }
}


