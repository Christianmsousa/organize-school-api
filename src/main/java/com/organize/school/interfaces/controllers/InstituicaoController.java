package com.organize.school.interfaces.controllers;

import com.organize.school.domain.Instituicao;
import com.organize.school.interfaces.json.InstituicaoPostJson;
import static  com.organize.school.security.TokenUtils.withUserId;
import com.organize.school.services.impl.InstituicaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/instituicao")
public class InstituicaoController {

    @Autowired
    InstituicaoServiceImpl instituicaoService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ResponseEntity<Instituicao> createInstituicao(@Valid @RequestBody InstituicaoPostJson instituicaoPostJson){
        return withUserId(id -> ResponseEntity.ok(instituicaoService.createInstituicao(instituicaoPostJson, Long.valueOf(id))));
    }

    @GetMapping
    public ResponseEntity<List<Instituicao>> findAllInstituicao(){
        return ResponseEntity.ok(instituicaoService.findAllInstituicao());
    }
}
