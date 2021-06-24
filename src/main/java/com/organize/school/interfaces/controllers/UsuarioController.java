package com.organize.school.interfaces.controllers;

import com.organize.school.domain.Usuario;
import com.organize.school.interfaces.json.UsuarioJson;
import com.organize.school.repository.UsuarioRepository;
import com.organize.school.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public Page<Usuario> getUsuario( Pageable page){
        return usuarioService.getAllUsuario(page);
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody UsuarioJson usuario){
        return ResponseEntity.ok(usuarioService.createUsuario(usuario));
    }
}

