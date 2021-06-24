package com.organize.school.interfaces.json;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoPost {

    private String nome;

    private Long instituicaoId;

}
