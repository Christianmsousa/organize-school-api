package com.organize.school.interfaces.controllers;

import com.organize.school.domain.Disciplina;
import com.organize.school.interfaces.json.DisciplinaPost;
import com.organize.school.services.impl.DisciplinaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.organize.school.security.TokenUtils.withUserId;

@RestController
@RequestMapping(path = "/disciplinas")
public class DisciplinaController {

    @Autowired
    DisciplinaServiceImpl disciplinaService;


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ResponseEntity<Disciplina> createDisciplina(@Valid @RequestBody DisciplinaPost disciplinaPost){
        return withUserId(id -> ResponseEntity.ok(disciplinaService.createDisciplina(disciplinaPost, Long.valueOf(id))));
    }
}
