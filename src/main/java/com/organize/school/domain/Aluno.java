package com.organize.school.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "aluno")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "aluno_turma",
            joinColumns = {@JoinColumn(name = "aluno_id")},
            inverseJoinColumns = {@JoinColumn(name = "turma_id")}
    )
    @JsonIgnoreProperties("aluno")
    private List<Turma> turmas;

    public Aluno(Usuario usuario) {
        this.usuario = usuario;
    }

    public Aluno(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
