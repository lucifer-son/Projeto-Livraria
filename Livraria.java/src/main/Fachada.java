package main;

import cadastro.*;
import cadastro.produto.*;
import cadastro.usuario.CadastroCliente;
import cadastro.usuario.CadastroUsuario;
import cadastro.CadastroWishList;
import model.*;
import excecoes.*;

public class Fachada {

    private static Fachada instance;

    private CadastroCliente cadastroCliente;
    private CadastroLivro cadastroLivro;
    private CadastroPedido cadastroPedido;
    private CadastroUsuario cadastroUsuario;
    private CadastroCarrinho cadastroCarrinho;
    private CadastroWishList cadastroWishList;
    private CadastroAvaliacao cadastroAvaliacao;
    private CadastroDevolucao cadastroDevolucao;
    private CadastroPagamento cadastroPagamento;
    private CadastroNotificacao cadastroNotificacao;
    private CadastroCupomPromocional cadastroCupomPromocional;

    private Fachada() {
        this.cadastroCliente = new CadastroCliente();
        this.cadastroLivro = new CadastroLivro();
        this.cadastroPedido = new CadastroPedido();
        this.cadastroUsuario = new CadastroUsuario();
        this.cadastroCarrinho = new CadastroCarrinho();
        this.cadastroWishList = new CadastroWishList();
        this.cadastroAvaliacao = new CadastroAvaliacao();
        this.cadastroDevolucao = new CadastroDevolucao();
        this.cadastroPagamento = new CadastroPagamento();
        this.cadastroNotificacao = new CadastroNotificacao();
        this.cadastroCupomPromocional = new CadastroCupomPromocional();
    }

    public static Fachada getInstance() {
        if (instance == null) {
            instance = new Fachada();
        }
        return instance;
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

    public void cadastrarLivro(Livro livro) throws EntidadeJaExistenteExcecao {
        cadastroLivro.cadastrar(livro);
    }
    public void atualizarLivro(Livro livro) throws EntidadeNaoEncontradaExcecao {
        cadastroLivro.atualizar(livro);
    }
    public void removerLivro(String id) throws EntidadeNaoEncontradaExcecao {
        cadastroLivro.remover(id);
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
    public void listarPedidos() {
        cadastroPedido.listar();
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

    public void cadastrarCarrinho(Carrinho carrinho) throws EntidadeJaExistenteExcecao {
        cadastroCarrinho.cadastrar(carrinho);
    }
    public void atualizarCarrinho(Carrinho carrinho) throws EntidadeNaoEncontradaExcecao {
        cadastroCarrinho.atualizar(carrinho);
    }
    public void removerCarrinho(String id) throws EntidadeNaoEncontradaExcecao {
        cadastroCarrinho.remover(id);
    }
    public Carrinho buscarCarrinhoPorId(String id) {
        return cadastroCarrinho.buscarPorId(id);
    }
    public void listarCarrinhos() {
        cadastroCarrinho.listar();
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

    public void cadastrarNotificacao(Notificacao notificacao) throws EntidadeJaExistenteExcecao {
        cadastroNotificacao.cadastrar(notificacao);
    }
    public void atualizarNotificacao(Notificacao notificacao) throws EntidadeNaoEncontradaExcecao {
        cadastroNotificacao.atualizar(notificacao);
    }
    public void removerNotificacao(String id) throws EntidadeNaoEncontradaExcecao {
        cadastroNotificacao.remover(id);
    }
    public Notificacao buscarNotificacaoPorId(String id) {
        return cadastroNotificacao.buscarPorId(id);
    }
    public void listarNotificacoes() {
        cadastroNotificacao.listar();
    }

    public void cadastrarCupomPromocional(CupomPromocional cupom) throws EntidadeJaExistenteExcecao {
        cadastroCupomPromocional.cadastrar(cupom);
    }
    public void atualizarCupomPromocional(CupomPromocional cupom) throws EntidadeNaoEncontradaExcecao {
        cadastroCupomPromocional.atualizar(cupom);
    }
    public void removerCupomPromocional(String id) throws EntidadeNaoEncontradaExcecao {
        cadastroCupomPromocional.remover(id);
    }
    public CupomPromocional buscarCupomPromocionalPorId(String id) {
        return cadastroCupomPromocional.buscarPorId(id);
    }
    public void listarCuponsPromocionais() {
        cadastroCupomPromocional.listar();
    }
}
