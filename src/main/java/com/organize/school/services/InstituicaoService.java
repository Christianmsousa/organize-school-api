package com.organize.school.services;

import com.organize.school.domain.Instituicao;
import com.organize.school.interfaces.json.InstituicaoPostJson;

import java.util.List;

public interface InstituicaoService {

    Instituicao createInstituicao(InstituicaoPostJson instituicaoPostJson, Long email);

    List<Instituicao> findAllInstituicao();
}
