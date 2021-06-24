package com.organize.school.services.impl;

import com.organize.school.domain.Aluno;
import com.organize.school.domain.Usuario;
import com.organize.school.exceptions.MessageError;
import com.organize.school.exceptions.Messages;
import com.organize.school.exceptions.PreconditionException;
import com.organize.school.interfaces.json.AlunoJson;
import com.organize.school.interfaces.json.PermissaoJson;
import com.organize.school.repository.AlunoRepository;
import com.organize.school.repository.PermissaoRepository;
import com.organize.school.repository.UsuarioRepository;
import com.organize.school.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    PermissaoRepository permissaoRepository;

    private final MessageError messageError;

    public AlunoServiceImpl(MessageError messageError) {
        this.messageError = messageError;
    }

    @Override
    public Aluno createAluno(AlunoJson alunoJson) {

        var emailSplit = alunoJson.getEmail().split("@");
        var email = emailSplit[1].toLowerCase();

        var validEmail = email.equals("gmail.com") || email.equals("hotmail.com");
        if(!validEmail){
            throw new PreconditionException(messageError.create(Messages.INVALID_EMAIL));
        }

        usuarioRepository.findOptionalByEmail(alunoJson.getEmail())
                .ifPresent(e -> {
                    throw new PreconditionException(messageError.create(Messages.USER_ALREADY_REGISTERED));
                });
        List<PermissaoJson> permissaos = List.of(PermissaoJson.builder().id(1L).build());
        alunoJson.setPermissoes(permissaos);


        Usuario usuario = Usuario.fromJson(alunoJson);
        usuarioRepository.save(usuario);
        Aluno aluno = new Aluno();
        aluno.setUsuario(usuario);


        return alunoRepository.save(aluno);
    }

    @Override
    public List<Aluno> getAllUsuarios() {
        return alunoRepository.findAll();
    }
}
