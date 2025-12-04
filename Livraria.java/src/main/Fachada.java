package main;

import cadastro.*;
import cadastro.produto.*;
import cadastro.usuario.CadastroCliente;
import cadastro.usuario.CadastroUsuario;
import cadastro.CadastroWishList;
import model.*;
import excecoes.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Fachada {

    private static Fachada instance;

    private CadastroCliente cadastroCliente;
    private CadastroLivro cadastroLivro;
    private CadastroPedido cadastroPedido;
    private CadastroUsuario cadastroUsuario;
    private CadastroWishList cadastroWishList;
    private CadastroAvaliacao cadastroAvaliacao;
    private CadastroDevolucao cadastroDevolucao;
    private CadastroPagamento cadastroPagamento;
    private CadastroCarrinho cadastroCarrinho;

    private Usuario usuarioLogado;

    private Fachada() {
        this.cadastroCliente = new CadastroCliente();
        this.cadastroLivro = new CadastroLivro();
        this.cadastroPedido = new CadastroPedido();
        this.cadastroUsuario = new CadastroUsuario();
        this.cadastroWishList = new CadastroWishList();
        this.cadastroAvaliacao = new CadastroAvaliacao();
        this.cadastroDevolucao = new CadastroDevolucao();
        this.cadastroPagamento = new CadastroPagamento();
        this.cadastroCarrinho = new CadastroCarrinho();
    }

    public static Fachada getInstance() {
        if (instance == null) {
            instance = new Fachada();
        }
        return instance;
    }


    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void logout() {
        this.usuarioLogado = null;
    }


    public boolean autenticar(String login, String senha) {
        Usuario usuarioGenerico = cadastroUsuario.buscarPorLogin(login);
        if (usuarioGenerico != null && usuarioGenerico.getSenha().equals(senha)) {
            System.out.println("DEBUG: Fachada.autenticar() - Usuário genérico/admin autenticado: " + login);
            usuarioLogado = usuarioGenerico;
            return true;
        }


        Cliente cliente = cadastroCliente.buscarPorEmail(login);
        if (cliente != null && cliente.getSenha().equals(senha)) {
            System.out.println("DEBUG: Fachada.autenticar() - Cliente autenticado: " + login);
            usuarioLogado = cliente;
            return true;
        }

        System.out.println("DEBUG: Fachada.autenticar() - Falha na autenticação para: " + login);
        return false;
    }


    private void verificarPermissaoAdmin() throws OperacaoNaoPermitidaExcecao {
        if (usuarioLogado == null || usuarioLogado.getTipo() != TipoUsuario.ADMIN) {
            throw new OperacaoNaoPermitidaExcecao("Acesso negado", "Operação de gerenciamento de livros", "Usuário não é ADMIN");
        }
    }

    public void cadastrarLivro(Livro livro) throws EntidadeJaExistenteExcecao, OperacaoNaoPermitidaExcecao {
        verificarPermissaoAdmin();
        cadastroLivro.cadastrar(livro);
    }
    public void atualizarLivro(Livro livro) throws EntidadeNaoEncontradaExcecao, OperacaoNaoPermitidaExcecao {
        verificarPermissaoAdmin();
        cadastroLivro.atualizar(livro);
    }
    public void removerLivro(String id) throws EntidadeNaoEncontradaExcecao, OperacaoNaoPermitidaExcecao {
        verificarPermissaoAdmin();
        cadastroLivro.remover(id);
    }


    public void cadastrarCliente(Cliente cliente) throws EntidadeJaExistenteExcecao {
        cadastroCliente.cadastrar(cliente);
    }
    public void atualizarCliente(Cliente cliente) throws EntidadeNaoEncontradaExcecao {
        cadastroCliente.atualizar(cliente);
    }
    public void removerCliente(String id) throws EntidadeNaoEncontradaExcecao {
        cadastroCliente.remover(id);
    }
    public Cliente buscarClientePorId(String id) {
        return cadastroCliente.buscarPorId(id);
    }
    public void listarClientes() {
        cadastroCliente.listar();
    }

    public Livro buscarLivroPorId(String id) {
        return cadastroLivro.buscarPorId(id);
    }
    public void listarLivros() {
        cadastroLivro.listar();
    }

    public void cadastrarPedido(Pedido pedido) throws EntidadeJaExistenteExcecao {
        cadastroPedido.cadastrar(pedido);
    }
    public void atualizarPedido(Pedido pedido) throws EntidadeNaoEncontradaExcecao {
        cadastroPedido.atualizar(pedido);
    }
    public void removerPedido(String id) throws EntidadeNaoEncontradaExcecao {
        cadastroPedido.remover(id);
    }
    public Pedido buscarPedidoPorId(String id) {
        return cadastroPedido.buscarPorId(id);
    }
    public List<Pedido> buscarTodosPedidos() {
        return cadastroPedido.buscarTodos();
    }
    public void listarPedidos() {
        cadastroPedido.listar();
    }

    public List<String> gerarRelatorioVendas(LocalDate inicio, LocalDate fim, String tipo) {
        return cadastroPedido.gerarRelatorioVendas(inicio, fim, tipo);
    }

    public void cadastrarUsuario(Usuario usuario) throws EntidadeJaExistenteExcecao {
        cadastroUsuario.cadastrar(usuario);
    }
    public void atualizarUsuario(Usuario usuario) throws EntidadeNaoEncontradaExcecao {
        cadastroUsuario.atualizar(usuario);
    }
    public void removerUsuario(String id) throws EntidadeNaoEncontradaExcecao {
        cadastroUsuario.remover(id);
    }
    public Usuario buscarUsuarioPorId(String id) {
        return cadastroUsuario.buscarPorId(id);
    }
    public void listarUsuarios() {
        cadastroUsuario.listar();
    }

    public void cadastrarWishList(WishList wishList) throws EntidadeJaExistenteExcecao {
        cadastroWishList.cadastrar(wishList);
    }
    public void atualizarWishList(WishList wishList) throws EntidadeNaoEncontradaExcecao {
        cadastroWishList.atualizar(wishList);
    }
    public void removerWishList(String id) throws EntidadeNaoEncontradaExcecao {
        cadastroWishList.remover(id);
    }
    public WishList buscarWishListPorId(String id) {
        return cadastroWishList.buscarPorId(id);
    }
    public void listarWishLists() {
        cadastroWishList.listar();
    }

    public void cadastrarAvaliacao(Avaliacao avaliacao) throws EntidadeJaExistenteExcecao {
        cadastroAvaliacao.cadastrar(avaliacao);
    }
    public void atualizarAvaliacao(Avaliacao avaliacao) throws EntidadeNaoEncontradaExcecao {
        cadastroAvaliacao.atualizar(avaliacao);
    }
    public void removerAvaliacao(String id) throws EntidadeNaoEncontradaExcecao {
        cadastroAvaliacao.remover(id);
    }
    public Avaliacao buscarAvaliacaoPorId(String id) {
        return cadastroAvaliacao.buscarPorId(id);
    }
    public void listarAvaliacoes() {
        cadastroAvaliacao.listar();
    }

    public void cadastrarDevolucao(Devolucao devolucao) throws EntidadeJaExistenteExcecao {
        cadastroDevolucao.cadastrar(devolucao);
    }
    public void atualizarDevolucao(Devolucao devolucao) throws EntidadeNaoEncontradaExcecao {
        cadastroDevolucao.atualizar(devolucao);
    }
    public void removerDevolucao(String id) throws EntidadeNaoEncontradaExcecao {
        cadastroDevolucao.remover(id);
    }
    public Devolucao buscarDevolucaoPorId(String id) {
        return cadastroDevolucao.buscarPorId(id);
    }
    public void listarDevolucoes() {
        cadastroDevolucao.listar();
    }

    public void cadastrarPagamento(Pagamento pagamento) throws EntidadeJaExistenteExcecao {
        cadastroPagamento.cadastrar(pagamento);
    }
    public void atualizarPagamento(Pagamento pagamento) throws EntidadeNaoEncontradaExcecao {
        cadastroPagamento.atualizar(pagamento);
    }
    public void removerPagamento(String id) throws EntidadeNaoEncontradaExcecao {
        cadastroPagamento.remover(id);
    }
    public Pagamento buscarPagamentoPorId(String id) {
        return cadastroPagamento.buscarPorId(id);
    }
    public void listarPagamentos() {
        cadastroPagamento.listar();
    }

    public void adicionarItemAoCarrinho(Livro livro, int quantidade) {
        cadastroCarrinho.adicionarItem(livro, quantidade);
    }

    public void removerItemDoCarrinho(Livro livro) {
        cadastroCarrinho.removerItem(livro);
    }

    public Map<Livro, Integer> getItensDoCarrinho() {
        return cadastroCarrinho.getItens();
    }

    public void limparCarrinho() {
        cadastroCarrinho.limparCarrinho();
    }

    public double getValorTotalCarrinho() {
        return cadastroCarrinho.getValorTotal();
    }

}
