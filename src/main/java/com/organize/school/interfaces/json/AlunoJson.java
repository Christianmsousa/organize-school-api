package com.organize.school.interfaces.json;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class AlunoJson {

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private int idade;

    @NotNull
    @Size(min = 5)
    private String senha;

    @NotNull
    private List<@NotNull PermissaoJson> permissoes;

    public AlunoJson(String nome, String email, int idade, String senha) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.senha = senha;
    }

    public AlunoJson(){

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

    public List<PermissaoJson> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<PermissaoJson> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public String toString() {
        return "AlunoJson{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                ", senha='" + senha + '\'' +
                ", permissoes=" + permissoes +
                '}';
    }
}
