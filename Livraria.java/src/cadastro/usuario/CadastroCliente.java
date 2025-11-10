package cadastro.usuario;

import model.Cliente;
import repositorio.ClienteRepositorioArray;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.util.List;
// import java.util.Optional; // Removido

/**
 * Camada de Serviço para Cliente, seguindo o exemplo do professor.
 * Esta classe agora está fortemente acoplada à sua implementação de repositório.
 */
public class CadastroCliente {

    private final ClienteRepositorioArray repositorio;

    /**
     * Construtor que cria sua própria instância do repositório,
     * como no exemplo do professor.
     */
    public CadastroCliente() {
        this.repositorio = new ClienteRepositorioArray();
    }

    public void cadastrar(Cliente cliente) throws EntidadeJaExistenteExcecao {
        // Verificação alterada de .isPresent() para != null
        if (repositorio.buscarPorId(cliente.getId()) != null) {
            throw new EntidadeJaExistenteExcecao(cliente.getId(), "Já existe um cliente com o ID " + cliente.getId());
        }
        // Verificação alterada de .isPresent() para != null
        if (repositorio.buscarPorEmail(cliente.getEmail()) != null) {
            throw new EntidadeJaExistenteExcecao(cliente.getEmail(), "Já existe um cliente com o e-mail " + cliente.getEmail());
        }
        repositorio.inserir(cliente);
    }

    public void atualizar(Cliente cliente) throws EntidadeNaoEncontradaExcecao {
        // Verificação alterada de .isEmpty() para == null
        if (repositorio.buscarPorId(cliente.getId()) == null) {
            throw new EntidadeNaoEncontradaExcecao(cliente.getId(), "Cliente não encontrado para atualização.");
        }
        repositorio.atualizar(cliente);
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {
        // Verificação alterada de .isEmpty() para == null
        if (repositorio.buscarPorId(id) == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "Cliente não encontrado para remoção.");
        }
        repositorio.deletarPorId(id);
    }

    // Retorno alterado de Optional<Cliente> para Cliente
    public Cliente buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Cliente> buscarTodos() {
        return repositorio.buscarTodos();
    }
    
    // Retorno alterado de Optional<Cliente> para Cliente
    public Cliente buscarPorEmail(String email) {
        return repositorio.buscarPorEmail(email);
    }

    public void listar() {
        for (Cliente cliente : repositorio.buscarTodos()) {
            System.out.println(cliente);
        }
    }
}
