package com.organize.school.services;


import com.organize.school.domain.Curso;
import com.organize.school.interfaces.json.CursoPost;

public interface CursoService {
    Curso createCurso(CursoPost cursoPost, Long userId);
}
