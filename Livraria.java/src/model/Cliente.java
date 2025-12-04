package model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import excecoes.EmailInvalidoExcecao;
import java.util.ArrayList;


public class Cliente extends Usuario {
    private List<String> telefones;
    private List<Endereco> enderecos;
    private Date dataRegistro;
    private WishList wishlist;
    private List<Pedido> historicoPedidos;


    public Cliente(String nome, String email, String senha) throws EmailInvalidoExcecao {
        super(nome, email, senha);
        this.telefones = new ArrayList<>();
        this.enderecos = new ArrayList<>();
        this.dataRegistro = new Date();
        this.wishlist = new WishList(this.getId() + "_wishlist");
        this.historicoPedidos = new ArrayList<>();
    }


    public Cliente(String id, String nome, String email, String senha, List<String> telefones, List<Endereco> enderecos, Date dataRegistro) throws EmailInvalidoExcecao {
        super(id, nome, email, senha);
        this.telefones = telefones;
        this.enderecos = enderecos;
        this.dataRegistro = dataRegistro;
        this.wishlist = new WishList(id + "_wishlist");
        this.historicoPedidos = new ArrayList<>();
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

    public Endereco getEndereco() {
        if (enderecos != null && !enderecos.isEmpty()) {
            return enderecos.get(0);
        }
        return null;
    }

    public void setEndereco(Endereco endereco) {
        if (enderecos == null) {
            enderecos = new ArrayList<>();
        }
        if (!enderecos.isEmpty()) {
            enderecos.set(0, endereco);
        } else {
            enderecos.add(endereco);
        }
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

    public List<Pedido> getHistoricoPedidos() {
        return historicoPedidos;
    }

    public void setHistoricoPedidos(List<Pedido> historicoPedidos) {
        this.historicoPedidos = historicoPedidos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + getId() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", tipo='" + getTipo() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(getId(), cliente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), telefones, enderecos, dataRegistro, wishlist, historicoPedidos);
    }
}
