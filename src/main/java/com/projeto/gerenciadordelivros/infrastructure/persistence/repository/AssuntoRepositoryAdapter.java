package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.port.AssuntoRepository;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AssuntoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssuntoRepositoryAdapter implements AssuntoRepository {

    private final AssuntoJpaRepository assuntoJpaRepository;

    public AssuntoRepositoryAdapter(AssuntoJpaRepository assuntoJpaRepository) {
        this.assuntoJpaRepository = assuntoJpaRepository;
    }

    @Override
    public Assunto salvar(Assunto assunto) {
        AssuntoEntity entity = new AssuntoEntity();
        entity.setDescricao(assunto.getDescricao());

        AssuntoEntity salvo = assuntoJpaRepository.save(entity);
        return new Assunto(salvo.getDescricao());
    }

    @Override
    public List<Assunto> listarTodos() {
        return assuntoJpaRepository.findAll().stream()
                .map(assuntoEntity -> new Assunto(assuntoEntity.getDescricao()))
                .toList();
    }
}
