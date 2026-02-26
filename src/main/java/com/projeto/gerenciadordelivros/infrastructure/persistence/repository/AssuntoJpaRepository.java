package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AssuntoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssuntoJpaRepository extends JpaRepository<AssuntoEntity, Long> {
}
