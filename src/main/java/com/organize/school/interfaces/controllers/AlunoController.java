package com.organize.school.interfaces.controllers;


import com.organize.school.domain.Aluno;
import com.organize.school.interfaces.json.AlunoJson;
import com.organize.school.services.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoServiceImpl alunoService;

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@Valid @RequestBody AlunoJson alunoJson){

        return ResponseEntity.ok(alunoService.createAluno(alunoJson));
    }

    @GetMapping
    public List<Aluno> getAll(){
        return alunoService.getAllUsuarios();
    }

}
