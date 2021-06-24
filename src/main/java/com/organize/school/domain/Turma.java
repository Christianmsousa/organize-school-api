package com.organize.school.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private Date horarioInicio;

    @Column
    private Date horarioFim;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "aluno_turma",
            joinColumns = {@JoinColumn(name = "aluno_id")},
            inverseJoinColumns = {@JoinColumn(name = "turma_id")}
    )
    @JsonIgnoreProperties("turma")
    private List<Aluno> alunos;

    @ManyToMany( cascade = CascadeType.DETACH)
    @JoinTable(
            name = "disciplina_turma",
            joinColumns = {@JoinColumn(name = "disciplina_id")},
            inverseJoinColumns = {@JoinColumn(name = "turma_id")}
    )
    @JsonIgnoreProperties("turmas")
    private List<Disciplina> disciplinas;


    public Turma(Long id, String nome, Date horarioInicio, Date horarioFim, List<Aluno> alunos, List<Disciplina> disciplinas) {
        this.id = id;
        this.nome = nome;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.alunos = alunos;
        this.disciplinas = disciplinas;
    }

    public Turma(){

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

    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(Date horarioFim) {
        this.horarioFim = horarioFim;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", horarioInicio=" + horarioInicio +
                ", horarioFim=" + horarioFim +
                ", alunos=" + alunos +
                ", disciplinas=" + disciplinas +
                '}';
    }
}
