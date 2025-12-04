package cadastro.usuario;

import model.Cliente;
import repositorio.ClienteRepositorioArquivojson;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;
import java.util.List;

public class CadastroCliente {

    private ClienteRepositorioArquivojson repositorio;


    public CadastroCliente() {
        this.repositorio = ClienteRepositorioArquivojson.getInstance();
    }

    public void cadastrar(Cliente cliente) throws EntidadeJaExistenteExcecao {
        System.out.println("DEBUG: CadastroCliente.cadastrar() - Tentando cadastrar cliente: " + cliente.getEmail());
        if (repositorio.buscarPorId(cliente.getId()) != null) {
            System.out.println("DEBUG: CadastroCliente.cadastrar() - Cliente com ID " + cliente.getId() + " já existe.");
            throw new EntidadeJaExistenteExcecao(cliente.getId(), "Já existe um cliente com o ID " + cliente.getId());
        }

        if (repositorio.buscarPorEmail(cliente.getEmail()) != null) {
            System.out.println("DEBUG: CadastroCliente.cadastrar() - Cliente com e-mail " + cliente.getEmail() + " já existe.");
            throw new EntidadeJaExistenteExcecao(cliente.getEmail(), "Já existe um cliente com o e-mail " + cliente.getEmail());
        }
        repositorio.inserir(cliente);
        System.out.println("DEBUG: CadastroCliente.cadastrar() - Cliente " + cliente.getEmail() + " inserido no repositório.");
    }

    public void atualizar(Cliente cliente) throws EntidadeNaoEncontradaExcecao {
        System.out.println("DEBUG: CadastroCliente.atualizar() - Tentando atualizar cliente: " + cliente.getEmail());
        if (repositorio.buscarPorId(cliente.getId()) == null) {
            System.out.println("DEBUG: CadastroCliente.atualizar() - Cliente com ID " + cliente.getId() + " não encontrado para atualização.");
            throw new EntidadeNaoEncontradaExcecao(cliente.getId(), "Cliente não encontrado para atualização.");
        }
        repositorio.atualizar(cliente);
        System.out.println("DEBUG: CadastroCliente.atualizar() - Cliente " + cliente.getEmail() + " atualizado no repositório.");
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        System.out.println("DEBUG: CadastroCliente.remover() - Tentando remover cliente com ID: " + id);
        if (repositorio.buscarPorId(id) == null) {
            System.out.println("DEBUG: CadastroCliente.remover() - Cliente com ID " + id + " não encontrado para remoção.");
            throw new EntidadeNaoEncontradaExcecao(id, "Cliente não encontrado para remoção.");
        }
        repositorio.deletarPorId(id);
        System.out.println("DEBUG: CadastroCliente.remover() - Cliente com ID " + id + " removido do repositório.");
    }


    public Cliente buscarPorId(String id) {
        System.out.println("DEBUG: CadastroCliente.buscarPorId() - Buscando cliente por ID: " + id);
        return repositorio.buscarPorId(id);
    }

    public List<Cliente> buscarTodos() {
        System.out.println("DEBUG: CadastroCliente.buscarTodos() - Buscando todos os clientes.");
        return repositorio.buscarTodos();
    }

    public Cliente buscarPorEmail(String email) {
        System.out.println("DEBUG: CadastroCliente.buscarPorEmail() - Buscando cliente por e-mail: " + email);
        return repositorio.buscarPorEmail(email);
    }

    public void listar() {
        System.out.println("DEBUG: CadastroCliente.listar() - Listando clientes.");
        for (Cliente cliente : repositorio.buscarTodos()) {
            System.out.println(cliente);
        }
    }
}
