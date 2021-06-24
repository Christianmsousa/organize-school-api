package com.organize.school.services.impl;

import com.organize.school.domain.Instituicao;
import com.organize.school.domain.Usuario;
import com.organize.school.exceptions.MessageError;
import com.organize.school.exceptions.Messages;
import com.organize.school.exceptions.PreconditionException;
import com.organize.school.interfaces.json.InstituicaoPostJson;
import com.organize.school.repository.InstituicaoRepository;
import com.organize.school.repository.UsuarioRepository;
import com.organize.school.services.InstituicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituicaoServiceImpl implements InstituicaoService {

    @Autowired
    InstituicaoRepository instituicaoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    private final MessageError messageError;

    Long userId;

    public InstituicaoServiceImpl(MessageError messageError) {
        this.messageError = messageError;
    }

    @Override
    public Instituicao createInstituicao(InstituicaoPostJson instituicaoPostJson, Long userId) {
        this.userId = userId;

        instituicaoRepository.findOptionalByNome(instituicaoPostJson.getNome()).ifPresent(e -> {
            throw new PreconditionException(messageError.create(Messages.INSTITUTION_ALREADY_REGISTERED));
        });

        Instituicao instituicao = Instituicao.fromJson(instituicaoPostJson);

        var user = usuarioRepository.findById(userId);

        List<Usuario> userList = List.of(user.get());
        instituicao.setAdministradores(userList);

        return instituicaoRepository.save(instituicao);
    }

    @Override
    public List<Instituicao> findAllInstituicao() {
        return instituicaoRepository.findAll();
    }
}
