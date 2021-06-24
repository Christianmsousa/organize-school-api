package com.organize.school.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "professor")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToMany( cascade = CascadeType.DETACH)
    @JoinTable(
            name = "professor_disciplina",
            joinColumns = {@JoinColumn(name = "professor_id")},
            inverseJoinColumns = {@JoinColumn(name = "disciplina_id")}
    )
    @JsonIgnoreProperties("professores")
    private List<Disciplina> disciplinas;


    public Professor(Long id, Usuario usuario, List<Disciplina> disciplinas) {
        this.id = id;
        this.usuario = usuario;
        this.disciplinas = disciplinas;
    }

    public Professor(){

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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", disciplinas=" + disciplinas +
                '}';
    }
}
