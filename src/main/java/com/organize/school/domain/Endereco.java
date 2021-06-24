package com.organize.school.domain;


import com.organize.school.interfaces.json.EnderecoJson;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String bairro;

    @Column()
    private String rua;

    @Column()
    private Long cep;


    public Endereco(String bairro, String rua, Long cep) {
        this.bairro = bairro;
        this.rua = rua;
        this.cep = cep;
    }

    public Endereco(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Long getCep() {
        return cep;
    }

    public void setCep(Long cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", bairro='" + bairro + '\'' +
                ", rua='" + rua + '\'' +
                ", cep=" + cep +
                '}';
    }
}
