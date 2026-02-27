package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AssuntoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AssuntoJpaRepository extends JpaRepository<AssuntoEntity, Long> {

    Optional<AssuntoEntity> findByDescricaoIgnoreCase(String descricao);

    @Query(value = "SELECT COUNT(*) FROM livro_assunto WHERE assunto_cod_as = :assuntoId", nativeQuery = true)
    long contarLivrosVinculados(@Param("assuntoId") Long assuntoId);
}
