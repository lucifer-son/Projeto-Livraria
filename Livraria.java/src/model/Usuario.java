package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Usuario {
    private String id; 
    private String login;
    private String senha;
    private Set<String> roles;

    public Usuario() {
        this.roles = new HashSet<>();
    }

    public Usuario(String id, String login, String senha) { 
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.roles = new HashSet<>();
    }

    public String getId() { 
        return id;
    }

    public void setId(String id) { 
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public boolean hasRole(String role) {
        if (roles == null) return false;
        return roles.contains(role);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' + 
                ", login='" + login + '\'' +
                ", roles=" + roles +
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