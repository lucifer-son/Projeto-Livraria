package cadastro.usuario;

import model.Cliente;
import repositorio.ClienteRepositorioArray;
import excecoes.EntidadeJaExistenteExcecao;
import excecoes.EntidadeNaoEncontradaExcecao;

import java.util.List;



public class CadastroCliente {

    private final ClienteRepositorioArray repositorio;


    public CadastroCliente() {
        this.repositorio = new ClienteRepositorioArray();
    }

    public void cadastrar(Cliente cliente) throws EntidadeJaExistenteExcecao {

        if (repositorio.buscarPorId(cliente.getId()) != null) {
            throw new EntidadeJaExistenteExcecao(cliente.getId(), "Já existe um cliente com o ID " + cliente.getId());
        }

        if (repositorio.buscarPorEmail(cliente.getEmail()) != null) {
            throw new EntidadeJaExistenteExcecao(cliente.getEmail(), "Já existe um cliente com o e-mail " + cliente.getEmail());
        }
        repositorio.inserir(cliente);
    }

    public void atualizar(Cliente cliente) throws EntidadeNaoEncontradaExcecao {

        if (repositorio.buscarPorId(cliente.getId()) == null) {
            throw new EntidadeNaoEncontradaExcecao(cliente.getId(), "Cliente não encontrado para atualização.");
        }
        repositorio.atualizar(cliente);
    }

    public void remover(String id) throws EntidadeNaoEncontradaExcecao {

        if (repositorio.buscarPorId(id) == null) {
            throw new EntidadeNaoEncontradaExcecao(id, "Cliente não encontrado para remoção.");
        }
        repositorio.deletarPorId(id);
    }


    public Cliente buscarPorId(String id) {
        return repositorio.buscarPorId(id);
    }

    public List<Cliente> buscarTodos() {
        return repositorio.buscarTodos();
    }
    
    public Cliente buscarPorEmail(String email) {
        return repositorio.buscarPorEmail(email);
    }

    public void listar() {
        for (Cliente cliente : repositorio.buscarTodos()) {
            System.out.println(cliente);
        }
    }
}
