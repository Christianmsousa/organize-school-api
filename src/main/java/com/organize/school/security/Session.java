package com.organize.school.security;

import com.organize.school.domain.Permissao;
import com.organize.school.domain.Usuario;
import org.springframework.security.core.userdetails.User;
import java.util.List;

public class Session extends User {

    Long id;

    public Session(Usuario usuario){
        super(usuario.getEmail(), usuario.getSenha(), usuario.getPermissoes());
        this.id = usuario.getId();
    }


    public Session(String username, String password, List<Permissao> authorities) {
        super(username, password, authorities);
    }

    public Long getId() {
        return id;
    }
}
