package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AutorJpaRepository extends JpaRepository<AutorEntity, Long> {

    Optional<AutorEntity> findByNomeIgnoreCase(String nome);

    @Query(value = "SELECT COUNT(*) FROM livro_autor WHERE autor_cod_au = :autorId", nativeQuery = true)
    long contarLivrosVinculados(@Param("autorId") Long autorId);
}
