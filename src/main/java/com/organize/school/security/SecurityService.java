package com.organize.school.security;

import com.organize.school.exceptions.MessageError;
import com.organize.school.exceptions.Messages;
import com.organize.school.exceptions.UserException;
import com.organize.school.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;



@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private MessageError messageError;

    public SecurityService(MessageError messageError) {
        this.messageError = messageError;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UserException {

        var usuario = usuarioRepository.findOptionalByEmail(email)
                .orElseThrow(() -> new UserException(messageError.create(Messages.USER_OR_PASSWORD_INVALID)));

        return new Session(usuario);
    }


}
