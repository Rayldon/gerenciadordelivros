package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.port.AssuntoRepository;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AssuntoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AssuntoRepositoryAdapter implements AssuntoRepository {

    private final AssuntoJpaRepository assuntoJpaRepository;

    public AssuntoRepositoryAdapter(AssuntoJpaRepository assuntoJpaRepository) {
        this.assuntoJpaRepository = assuntoJpaRepository;
    }

    @Override
    public Assunto salvar(Assunto assunto) {
        AssuntoEntity entity = new AssuntoEntity();
        entity.setId(assunto.getId());
        entity.setDescricao(assunto.getDescricao());

        AssuntoEntity salvo = assuntoJpaRepository.save(entity);
        return new Assunto(salvo.getId(), salvo.getDescricao());
    }

    @Override
    public List<Assunto> listarTodos() {
        return assuntoJpaRepository.findAll().stream()
                .map(assuntoEntity -> new Assunto(assuntoEntity.getId(), assuntoEntity.getDescricao()))
                .toList();
    }

    @Override
    public Optional<Assunto> buscarPorId(Long id) {
        return assuntoJpaRepository.findById(id)
                .map(assuntoEntity -> new Assunto(assuntoEntity.getId(), assuntoEntity.getDescricao()));
    }

    @Override
    public Optional<Assunto> atualizar(Long id, Assunto assunto) {
        return assuntoJpaRepository.findById(id)
                .map(entity -> {
                    entity.setDescricao(assunto.getDescricao());
                    AssuntoEntity salvo = assuntoJpaRepository.save(entity);
                    return new Assunto(salvo.getId(), salvo.getDescricao());
                });
    }

    @Override
    public boolean existePorId(Long id) {
        return assuntoJpaRepository.existsById(id);
    }

    @Override
    public void deletarPorId(Long id) {
        assuntoJpaRepository.deleteById(id);
    }

    @Override
    public boolean possuiLivrosVinculados(Long id) {
        return assuntoJpaRepository.contarLivrosVinculados(id) > 0;
    }
}
