package com.organize.school.interfaces.controllers;

import com.organize.school.domain.Curso;
import com.organize.school.interfaces.json.CursoPost;
import com.organize.school.services.impl.CursoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static  com.organize.school.security.TokenUtils.withUserId;

@RestController
@RequestMapping(path = "/cursos")
public class CursoController {

    @Autowired
    CursoServiceImpl cursoService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ResponseEntity<Curso> createCurso(@Valid @RequestBody  CursoPost cursoPost){
        return withUserId(id -> ResponseEntity.ok(cursoService.createCurso(cursoPost, Long.valueOf(id))));
    }
}
