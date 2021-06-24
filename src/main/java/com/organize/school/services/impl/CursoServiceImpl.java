package com.organize.school.services.impl;

import com.organize.school.domain.Curso;
import com.organize.school.exceptions.MessageError;
import com.organize.school.exceptions.Messages;
import com.organize.school.exceptions.PreconditionException;
import com.organize.school.interfaces.json.CursoPost;
import com.organize.school.repository.CursoRepository;
import com.organize.school.repository.InstituicaoRepository;
import com.organize.school.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    InstituicaoRepository instituicaoRepository;

    @Autowired
    CursoRepository cursoRepository;

    Long userId;

    private final MessageError messageError;

    public CursoServiceImpl(MessageError messageError) {
        this.messageError = messageError;
    }

    @Override
    public Curso createCurso(CursoPost cursoPost, Long userId) {

        this.userId = userId;

        var instituicao = instituicaoRepository.findById(cursoPost.getInstituicaoId()).orElseThrow(
                () -> new PreconditionException(messageError.create(Messages.INSTITUTION_NOT_FOUND))
        );
        var existsCurso= instituicao.getCursos().stream().anyMatch( e -> e.getNome().equals(cursoPost.getNome()) && e.getInstituicao().equals(instituicao));
        if (existsCurso) {
            throw new PreconditionException(messageError.create(Messages.COURSE_ALREADY_REGISTERED));
        }
        Curso curso = new Curso(cursoPost.getNome(), instituicao);

        return cursoRepository.save(curso);
    }
}
