package com.organize.school.config;

import com.organize.school.domain.Permissao;
import com.organize.school.security.Session;
import com.organize.school.security.TokenUtils;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AuthMiddleware extends OncePerRequestFilter {


    // Verificação de token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = TokenUtils.recuperarTokenDaRequest(request);

        if (Objects.nonNull(token)){

            try {
                var parsed = Jwts.parser().setSigningKey("Teste").parseClaimsJws(token);
                var username = parsed.getBody().getSubject();
//                var username = parsed.getBody().getSubject();
                var permissoes = Arrays.stream(parsed.getBody().get("roles").toString().split(","))
                        .map(Permissao::new)
                        .collect(Collectors.toList());
                var session = new Session(username, "Teste",permissoes);

                // Logando usuario
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(session,"Teste",permissoes));

            }catch (Exception e){
                System.out.println("Token invalido, expirado ou ausente");
            }

        }
        // Filtragem
        filterChain.doFilter(request, response);

    }
}
