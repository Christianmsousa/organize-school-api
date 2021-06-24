package com.organize.school.interfaces.json;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class InstituicaoPostJson {

    @NotBlank
    private String nome;

    @NotNull
    private EnderecoJson endereco;

    @NotBlank
    private String tipoInstituicao;

//    @NotNull
//    private List<UsuarioAdministradorJson> administradores;

    public InstituicaoPostJson(String nome, EnderecoJson enderecoJson, String tipoInstituicao){
        this.nome = nome;
        this.endereco = enderecoJson;
        this.tipoInstituicao = tipoInstituicao;
    }


}
