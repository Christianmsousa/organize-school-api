package com.organize.school.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tipo_instituicao")
public class TipoInstituicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;


    public TipoInstituicao(String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TipoInstituicao(){

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

    @Override
    public String toString() {
        return "TipoInstituicao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
