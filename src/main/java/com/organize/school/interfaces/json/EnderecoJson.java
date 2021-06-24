package com.organize.school.interfaces.json;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoJson {

    @NotBlank
    private String bairro;

    @NotBlank
    private String rua;

    @NotNull
    private Long cep;
}
