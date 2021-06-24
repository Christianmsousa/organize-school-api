package com.organize.school.repository;

import com.organize.school.domain.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao,Long> {
    Optional<Instituicao> findOptionalByNome(String nome);

    List<Instituicao> findByAdministradores_Id(Long id);
}
