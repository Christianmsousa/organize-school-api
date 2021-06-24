package com.organize.school.services;


import com.organize.school.domain.Usuario;
import com.organize.school.interfaces.json.UsuarioJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
    Usuario createUsuario(UsuarioJson usuarioJson);

    Page<Usuario> getAllUsuario(Pageable page);
}
