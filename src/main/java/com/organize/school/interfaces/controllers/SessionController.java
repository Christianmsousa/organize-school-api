package com.organize.school.interfaces.controllers;


import com.organize.school.interfaces.json.UsuarioPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public String createSession(@RequestBody UsuarioPost usuarioPost){
        return usuarioPost.buildToken(authManager);
    }
}
