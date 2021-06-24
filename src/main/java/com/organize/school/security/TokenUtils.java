package com.organize.school.security;


import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.function.Function;

public class TokenUtils {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String ACCEPTABLE_TOKEN_TYPE = "Bearer ";

    /**
     * Recupera o token de dentro do cabeçalho da request.
     * @param request {request que chegou na API}
     * @return null se o token estiver vazio ou for do tipo errado.
     */
    public static String recuperarTokenDaRequest(HttpServletRequest request) {
        var token = request.getHeader(AUTHORIZATION_HEADER);

        if (tokenIsNull(token)) return null;

        return token.substring(7, token.length());
    }

    /**
     * Verifica se o token esta vazio ou não é
     * do tipo 'Bearer'.
     * @param token
     * @return true se o token for vazio ou não for bearer.
     */
    private static boolean tokenIsNull(String token) {
        return Objects.isNull(token) || token.isEmpty() || !token.startsWith(ACCEPTABLE_TOKEN_TYPE);
    }

    private static String getUserIdFromToken(SecurityContext securityContext){
        var principal = (UserDetails) securityContext.getAuthentication().getPrincipal();

        return principal.getUsername();

    }
    public static <R> R withUserId(Function<String, R> f) {
        var subject = getUserIdFromToken(SecurityContextHolder.getContext());
        if(subject != null ) return f.apply(subject);
        throw new UsernameNotFoundException("Não o usuario");
    }
}