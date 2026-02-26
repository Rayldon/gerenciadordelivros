package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroJpaRepository extends JpaRepository<LivroEntity, Long> {
}
