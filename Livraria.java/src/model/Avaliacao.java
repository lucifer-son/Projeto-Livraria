package model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Avaliacao {
    private String id;
    private Livro livro;
    private Cliente cliente;
    private int nota;
    private String comentario;
    private Date data;
    private boolean aprovado;

    public Avaliacao(String id, int nota, String comentario, Date data, boolean aprovado) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.data = data;
        this.aprovado = aprovado;
    }

    public Avaliacao(Livro livro, Cliente cliente, int nota, String comentario) {
        this.id = UUID.randomUUID().toString();
        this.livro = livro;
        this.cliente = cliente;
        this.nota = nota;
        this.comentario = comentario;
        this.data = new Date();
        this.aprovado = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id='" + id + '\'' +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return Objects.equals(id, avaliacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
