package model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import excecoes.EmailInvalidoExcecao;
import java.util.ArrayList;


public class Cliente {
    private String id;
    private String nome;
    private String email;
    private String senha;
    private List<String> telefones;
    private List<Endereco> enderecos;
    private Date dataRegistro;
    private WishList wishlist;
    private Carrinho carrinho;
    private List<Pedido> historicoPedidos;

    public Cliente(String id, String nome, String email, String senha, List<String> telefones, List<Endereco> enderecos, Date dataRegistro) throws EmailInvalidoExcecao {
     
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new EmailInvalidoExcecao("Formato de e-mail inválido: " + email);
        }
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefones = telefones;
        this.enderecos = enderecos;
        this.dataRegistro = dataRegistro;
        this.wishlist = new WishList(id + "_wishlist"); 
        this.historicoPedidos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public WishList getWishlist() {
        return wishlist;
    }

    public void setWishlist(WishList wishlist) {
        this.wishlist = wishlist;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public List<Pedido> getHistoricoPedidos() {
        return historicoPedidos;
    }

    public void setHistoricoPedidos(List<Pedido> historicoPedidos) {
        this.historicoPedidos = historicoPedidos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
