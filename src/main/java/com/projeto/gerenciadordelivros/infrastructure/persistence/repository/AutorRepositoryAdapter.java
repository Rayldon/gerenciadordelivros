package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.domain.port.AutorRepository;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AutorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AutorRepositoryAdapter implements AutorRepository {

    private final AutorJpaRepository autorJpaRepository;

    @Override
    public Autor salvar(Autor autor) {
        AutorEntity entity = new AutorEntity();
        entity.setId(autor.getId());
        entity.setNome(autor.getNome());

        AutorEntity salvo = autorJpaRepository.save(entity);
        return new Autor(salvo.getId(), salvo.getNome());
    }

    @Override
    public List<Autor> listarTodos() {
        return autorJpaRepository.findAll().stream()
                .map(autorEntity -> new Autor(autorEntity.getId(), autorEntity.getNome()))
                .toList();
    }

    @Override
    public Optional<Autor> buscarPorId(Long id) {
        return autorJpaRepository.findById(id)
                .map(autorEntity -> new Autor(autorEntity.getId(), autorEntity.getNome()));
    }

    @Override
    public Optional<Autor> atualizar(Long id, Autor autor) {
        return autorJpaRepository.findById(id)
                .map(entity -> {
                    entity.setNome(autor.getNome());
                    AutorEntity salvo = autorJpaRepository.save(entity);
                    return new Autor(salvo.getId(), salvo.getNome());
                });
    }

    @Override
    public boolean existePorId(Long id) {
        return autorJpaRepository.existsById(id);
    }

    @Override
    public void deletarPorId(Long id) {
        autorJpaRepository.deleteById(id);
    }

    @Override
    public boolean possuiLivrosVinculados(Long id) {
        return autorJpaRepository.contarLivrosVinculados(id) > 0;
    }
}
