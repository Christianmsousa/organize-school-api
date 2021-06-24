package com.organize.school.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.organize.school.interfaces.json.AlunoJson;
import com.organize.school.interfaces.json.UsuarioJson;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuario")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int idade;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(nullable = false)
    private String senha;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "usuario_permissao",
            joinColumns = {@JoinColumn(name = "usuario_id")},
            inverseJoinColumns = {@JoinColumn(name = "permissao_id")}
    )
    @JsonIgnore
    private List<Permissao> permissoes;

    public Usuario(String nome, String email, int idade, String senha, List<Permissao> permissoes) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.senha = senha;
        this.permissoes = permissoes;
    }

    public Usuario(){

    }

    public static Usuario fromJson(AlunoJson alunoJson){

        return new Usuario(
                alunoJson.getNome().toLowerCase(),
                alunoJson.getEmail().toLowerCase(),
                alunoJson.getIdade(),
                alunoJson.getSenha(),
                alunoJson.getPermissoes().stream().map(permissao -> new Permissao(permissao.getId())).collect(Collectors.toList())
        );
    }
    public static Usuario fromJson(UsuarioJson usuarioJson){

        return new Usuario(
                usuarioJson.getNome().toLowerCase(),
                usuarioJson.getEmail().toLowerCase(),
                usuarioJson.getIdade(),
                usuarioJson.getSenha(),
                usuarioJson.getPermissoes().stream().map(permissao -> new Permissao(permissao.getId())).collect(Collectors.toList())
        );
    }

    @PrePersist
    private void created() {
        this.senha = new BCryptPasswordEncoder()
                .encode(senha);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                ", senha='" + senha + '\'' +
                ", permissoes=" + permissoes +
                '}';
    }
}
