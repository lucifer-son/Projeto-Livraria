package model;

import excecoes.EstoqueInvalidoExcecao;
import excecoes.EstoqueInsuficienteExcecao;
import excecoes.PrecoInvalidoExcecao;

import java.util.List;
import java.util.Objects;

public class Livro {
    private String id;
    private String titulo;
    private List<String> autores;
    private String editora;
    private String isbn;
    private int anoPublicacao;
    private String descricao;
    private double preco;
    private int estoque;
    private double peso;
    private String categoria;
    private String imagem;
    private int numPaginas;
    private List<Avaliacao> avaliacoes;

    public Livro() {}

    public Livro(String id, String titulo, List<String> autores, String editora, String isbn, int anoPublicacao, String descricao, double preco, int estoque, double peso, String categoria, String imagem, int numPaginas) throws EstoqueInvalidoExcecao, PrecoInvalidoExcecao {
        if (preco <= 0) {
            throw new PrecoInvalidoExcecao("O preço do livro deve ser maior que zero.");
        }
        this.id = id;
        this.titulo = titulo;
        this.autores = autores;
        this.editora = editora;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.descricao = descricao;
        this.preco = preco;
        setEstoque(estoque);
        this.peso = peso;
        this.categoria = categoria;
        this.imagem = imagem;
        this.numPaginas = numPaginas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) throws PrecoInvalidoExcecao {
        if (preco <= 0) {
            throw new PrecoInvalidoExcecao("O preço do livro deve ser maior que zero.");
        }
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) throws EstoqueInvalidoExcecao {
        if (estoque < 0) {
            throw new EstoqueInvalidoExcecao(this.id, estoque, "O estoque do livro não pode ser negativo.");
        }
        this.estoque = estoque;
    }

    public void diminuirEstoque(int quantidade) throws EstoqueInsuficienteExcecao, EstoqueInvalidoExcecao {
        if (quantidade < 0) {
            throw new EstoqueInvalidoExcecao(this.id, quantidade, "Não é possível diminuir o estoque com uma quantidade negativa.");
        }
        if (this.estoque < quantidade) {
            throw new EstoqueInsuficienteExcecao(this.id, quantidade, this.estoque);
        }
        setEstoque(this.estoque - quantidade);
    }

    public void aumentarEstoque(int quantidade) throws EstoqueInvalidoExcecao {
        if (quantidade < 0) {
            throw new EstoqueInvalidoExcecao(this.id, quantidade, "Não é possível aumentar o estoque com uma quantidade negativa.");
        }
        setEstoque(this.estoque + quantidade);
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
