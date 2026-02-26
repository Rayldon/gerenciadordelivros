package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorJpaRepository extends JpaRepository<AutorEntity, Long> {
}
