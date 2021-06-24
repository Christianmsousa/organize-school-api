package com.organize.school.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "curso")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column()
    private Long cargaHoraria;

    @ManyToOne()
    @JoinColumn(name = "instituicao_id")
    @JsonIgnoreProperties("cursos")
    private Instituicao instituicao;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "disciplina_curso",
            joinColumns = {@JoinColumn(name = "disciplina_id")},
            inverseJoinColumns = {@JoinColumn(name = "curso_id")}
    )
    private Set<Disciplina> disciplinas = new HashSet<>();

    public void addCurso(Disciplina disciplina){
        this.disciplinas.add(disciplina);
    }
    public Curso(String nome, Instituicao instituicao) {
        this.nome = nome;
        this.instituicao = instituicao;
    }

    public Curso(){

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

    public Long getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Long cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Set<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                ", instituicao=" + instituicao +
                ", disciplinas=" + disciplinas +
                '}';
    }
}
