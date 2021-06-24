package com.organize.school.services.impl;

import com.organize.school.domain.Disciplina;
import com.organize.school.exceptions.MessageError;
import com.organize.school.exceptions.Messages;
import com.organize.school.exceptions.PreconditionException;
import com.organize.school.interfaces.json.DisciplinaPost;
import com.organize.school.repository.CursoRepository;
import com.organize.school.repository.DisciplinaRepository;
import com.organize.school.repository.InstituicaoRepository;
import com.organize.school.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    private final MessageError messageError;

    public DisciplinaServiceImpl(MessageError messageError) {
        this.messageError = messageError;
    }

    Long userId;



    @Override
    public Disciplina createDisciplina(DisciplinaPost disciplinaPost, Long id) {

        this.userId = userId;

        // Verificar se o usuario é administrador dessa instituição
        var instituicao = this.instituicaoRepository.findById(disciplinaPost.getInstituicaoId())
                .orElseThrow(() -> new PreconditionException(messageError.create(Messages.INSTITUTION_NOT_FOUND)));

        var userPermissao = instituicao.getAdministradores().stream()
                .anyMatch(e -> e.getId().equals(userId));

        if(userPermissao){
            throw new PreconditionException(messageError.create(Messages.NOT_PERMISSION));
        }

        // Verificar se contem essa disciplina nesse curso
        var curso = this.cursoRepository.findById(disciplinaPost.getCursoId())
                .orElseThrow( () -> new PreconditionException(messageError.create(Messages.COURSE_NOT_EXIST)));

        var cursoValidation = curso.getInstituicao().getId().equals(disciplinaPost.getInstituicaoId());

        if(!cursoValidation){
            throw new PreconditionException(messageError.create(Messages.COURSE_NOT_EXIST));
        }

        var disciplinaExists = curso.getDisciplinas().stream()
                .anyMatch( disciplina -> disciplina.getNome().equalsIgnoreCase(disciplinaPost.getNome()));

        if(disciplinaExists){
            throw new PreconditionException(messageError.create(Messages.SUBJECT_ALREADY_REGISTERED));
        }

        var disciplinaDomain = new Disciplina(disciplinaPost.getNome(), disciplinaPost.getCargaHoraria());

        var disciplinaCreated = this.disciplinaRepository.save(disciplinaDomain);

        curso.addCurso(disciplinaCreated);
        this.cursoRepository.save(curso);

        return disciplinaCreated;
    }
}
