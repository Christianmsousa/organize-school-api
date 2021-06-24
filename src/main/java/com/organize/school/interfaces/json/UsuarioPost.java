package com.organize.school.interfaces.json;

import com.organize.school.security.Session;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class UsuarioPost {

    private String email;

    private String senha;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }

    private String roleFormatter(Collection<GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }


    /**
     * Gera uma data de expiração.
     * Cria um date que equivale a HOJE + UM DIA
     * @return {Date} mais um dia.
     */
    private Date getExpirationDate() {

        var instant = LocalDateTime
                .now()
                .plusHours(24)
                .atZone(ZoneId.systemDefault())
                .toInstant();

        return Date.from(instant);
    }


    public String buildToken(AuthenticationManager auth) {

        var HOJE = new Date();

        var session = (Session) auth.authenticate(toAuthenticationToken()).getPrincipal();

        return Jwts.builder()
                .setIssuer("com.organize.school")
                .setSubject(session.getId().toString())
//                .setSubject(session.getUsername())
//                .setId(session.getId().toString())
                .claim("roles", roleFormatter(session.getAuthorities()))
                .setIssuedAt(HOJE)
                .setExpiration(getExpirationDate())
                .signWith(SignatureAlgorithm.HS256, "Teste")
                .compact();
    }
}
