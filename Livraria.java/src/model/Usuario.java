package model;

import excecoes.EmailInvalidoExcecao;

import java.util.Objects;
import java.util.UUID;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipo;

    public Usuario() {

    }


    public Usuario(String id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = determineTipoUsuario(email);
    }


    public Usuario(String nome, String email, String senha) throws EmailInvalidoExcecao {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new EmailInvalidoExcecao("Formato de e-mail inválido: " + email);
        }
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = determineTipoUsuario(email);
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
    
    public String getLogin() {
        return email;
    }

    public void setEmail(String email) throws EmailInvalidoExcecao {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new EmailInvalidoExcecao("Formato de e-mail inválido: " + email);
        }
        this.email = email;
        this.tipo = determineTipoUsuario(email);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }


    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo; 
    }


    private TipoUsuario determineTipoUsuario(String email) {
        if (email != null && email.toLowerCase().endsWith("@ufrpe.br")) {
            return TipoUsuario.ADMIN;
        }
        return TipoUsuario.CLIENTE;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", tipo=" + tipo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
