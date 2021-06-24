package com.organize.school.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String nome;

    @Column()
    private Long cargaHoraria;

    @ManyToMany( cascade = CascadeType.DETACH)
    @JoinTable(
            name = "professor_disciplina",
            joinColumns = {@JoinColumn(name = "professor_id")},
            inverseJoinColumns = {@JoinColumn(name = "disciplina_id")}
    )
    @JsonIgnoreProperties("disciplinas")
    private List<Professor> professores;

    @ManyToMany( cascade = CascadeType.DETACH)
    @JoinTable(
            name = "disciplina_turma",
            joinColumns = {@JoinColumn(name = "disciplina_id")},
            inverseJoinColumns = {@JoinColumn(name = "turma_id")}
    )
    @JsonIgnoreProperties("disciplinas")
    private List<Turma> turmas;


    public Disciplina(Long id, String nome, Long cargaHoraria, List<Professor> professores, List<Turma> turmas) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professores = professores;
        this.turmas = turmas;
    }
    public Disciplina(String nome, Long cargaHoraria){
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    public Disciplina(){

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

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                ", professores=" + professores +
                ", turmas=" + turmas +
                '}';
    }
}
