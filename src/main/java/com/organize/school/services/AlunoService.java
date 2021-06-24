package com.organize.school.services;

import com.organize.school.domain.Aluno;
import com.organize.school.interfaces.json.AlunoJson;

import java.util.List;

public interface AlunoService {
    Aluno createAluno(AlunoJson alunoJson);

    List<Aluno> getAllUsuarios();
}
