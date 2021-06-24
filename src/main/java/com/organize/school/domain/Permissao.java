package com.organize.school.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@Entity
@Table(name = "permissao")
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Permissao implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    public Permissao(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Permissao(){

    }
    public Permissao(String nome){
        this.nome = nome;
    }

    public Permissao(Long id){
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getAuthority();
    }

    @Override
    public String getAuthority() {
        return getNome();
    }


}
