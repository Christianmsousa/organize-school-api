package com.organize.school.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.organize.school.interfaces.json.InstituicaoPostJson;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "instituicao")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "instituicao")
    @JsonIgnoreProperties("instituicao")
    private List<Curso> cursos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_instituicao_id", referencedColumnName = "id")
    private TipoInstituicao tipo;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "administradores")
    List<Usuario> administradores;

    public Instituicao(Long id, String nome, List<Curso> cursos, Endereco endereco, TipoInstituicao tipo) {
        this.id = id;
        this.nome = nome;
        this.cursos = cursos;
        this.endereco = endereco;
        this.tipo = tipo;
    }

    public Instituicao(){

    }
    public Instituicao(String nome, Endereco endereco, TipoInstituicao tipo){
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
    }
    public static Instituicao fromJson(InstituicaoPostJson instituicaoPostJson){

        Endereco enderecoFromJson = new Endereco(
                instituicaoPostJson.getEndereco().getBairro(),
                instituicaoPostJson.getEndereco().getRua(),
                instituicaoPostJson.getEndereco().getCep()
                );

        TipoInstituicao tipoInstituicaoFromJson = new TipoInstituicao(
                instituicaoPostJson.getTipoInstituicao()
                );

        return new Instituicao(
                instituicaoPostJson.getNome(),
                enderecoFromJson,
                tipoInstituicaoFromJson
        );
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

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoInstituicao getTipo() {
        return tipo;
    }

    public void setTipo(TipoInstituicao tipo) {
        this.tipo = tipo;
    }



    public List<Usuario> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<Usuario> administradores) {
        this.administradores = administradores;
    }

    @Override
    public String toString() {
        return "Instituicao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cursos=" + cursos +
                ", endereco=" + endereco +
                ", tipo=" + tipo +
                ", administradores=" + administradores +
                '}';
    }
}
