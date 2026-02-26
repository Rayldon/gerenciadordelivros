package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
import com.projeto.gerenciadordelivros.infrastructure.persistence.mapper.LivroEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LivroRepositoryAdapter implements LivroRepository {

    private final LivroJpaRepository livroJpaRepository;
    private final LivroEntityMapper livroEntityMapper;

    public LivroRepositoryAdapter(LivroJpaRepository livroJpaRepository, LivroEntityMapper livroEntityMapper) {
        this.livroJpaRepository = livroJpaRepository;
        this.livroEntityMapper = livroEntityMapper;
    }

    @Override
    public Livro salvar(Livro livro) {
        return livroEntityMapper.toDomain(
                livroJpaRepository.save(livroEntityMapper.toEntity(livro))
        );
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {
        return livroJpaRepository.findById(id).map(livroEntityMapper::toDomain);
    }

    @Override
    public List<Livro> listarTodos() {
        return livroJpaRepository.findAll().stream()
                .map(livroEntityMapper::toDomain)
                .toList();
    }
}
