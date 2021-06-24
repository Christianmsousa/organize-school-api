package com.organize.school.services;

import com.organize.school.domain.Disciplina;
import com.organize.school.interfaces.json.DisciplinaPost;

public interface DisciplinaService {

    Disciplina createDisciplina(DisciplinaPost disciplinaPost, Long userId);
}
