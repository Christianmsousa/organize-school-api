package com.organize.school.services.impl;

import com.organize.school.domain.Permissao;
import com.organize.school.domain.Usuario;
import com.organize.school.exceptions.MessageError;
import com.organize.school.exceptions.Messages;
import com.organize.school.exceptions.NotFoundException;
import com.organize.school.exceptions.PreconditionException;
import com.organize.school.interfaces.json.PermissaoJson;
import com.organize.school.interfaces.json.UsuarioJson;
import com.organize.school.repository.PermissaoRepository;
import com.organize.school.repository.UsuarioRepository;
import com.organize.school.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PermissaoRepository permissaoRepository;

    private final MessageError messageError;

    public UsuarioServiceImpl(MessageError messageError){
        this.messageError = messageError;
    }

    @Override
    public Usuario createUsuario(UsuarioJson usuarioJson) {

        var emailSplit = usuarioJson.getEmail().split("@");
        var email = emailSplit[1].toLowerCase();

        var validEmail = email.equals("gmail.com") || email.equals("hotmail.com");

        if(!validEmail){
            throw new PreconditionException(messageError.create(Messages.INVALID_EMAIL));
        }

        usuarioRepository.findOptionalByEmail(usuarioJson.getEmail())
                .ifPresent(e ->{
                    throw new PreconditionException(messageError.create(Messages.USER_ALREADY_REGISTERED));
                });
        List<PermissaoJson> permissaos = List.of(PermissaoJson.builder().id(2L).build());
        usuarioJson.setPermissoes(permissaos);


        Usuario usuario = Usuario.fromJson(usuarioJson);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Page<Usuario> getAllUsuario(Pageable page) {
        return usuarioRepository.findAll(page);
    }
}
