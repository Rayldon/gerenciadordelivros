package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.domain.port.AutorRepository;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AutorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AutorRepositoryAdapter implements AutorRepository {

    private final AutorJpaRepository autorJpaRepository;

    public AutorRepositoryAdapter(AutorJpaRepository autorJpaRepository) {
        this.autorJpaRepository = autorJpaRepository;
    }

    @Override
    public Autor salvar(Autor autor) {
        AutorEntity entity = new AutorEntity();
        entity.setNome(autor.getNome());

        AutorEntity salvo = autorJpaRepository.save(entity);
        return new Autor(salvo.getNome());
    }

    @Override
    public List<Autor> listarTodos() {
        return autorJpaRepository.findAll().stream()
                .map(autorEntity -> new Autor(autorEntity.getNome()))
                .toList();
    }
}
