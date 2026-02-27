package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
import com.projeto.gerenciadordelivros.domain.port.Paginacao;
import com.projeto.gerenciadordelivros.infrastructure.persistence.mapper.LivroEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LivroRepositoryAdapter implements LivroRepository {

    private final LivroJpaRepository livroJpaRepository;
    private final LivroEntityMapper livroEntityMapper;

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
    public Paginacao<Livro> listarPaginado(int pagina, int tamanho) {
        var page = livroJpaRepository.findAll(PageRequest.of(pagina, tamanho));

        return new Paginacao<>(
                page.getContent().stream().map(livroEntityMapper::toDomain).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }

    @Override
    public Optional<Livro> atualizar(Long id, Livro livro) {
        return livroJpaRepository.findById(id)
                .map(entity -> {
                    var atualizado = livroEntityMapper.toEntity(livro);
                    entity.setTitulo(atualizado.getTitulo());
                    entity.setValor(atualizado.getValor());
                    entity.setAutores(atualizado.getAutores());
                    entity.setAssuntos(atualizado.getAssuntos());
                    return livroEntityMapper.toDomain(livroJpaRepository.save(entity));
                });
    }

    @Override
    public boolean existePorId(Long id) {
        return livroJpaRepository.existsById(id);
    }

    @Override
    public void deletarPorId(Long id) {
        livroJpaRepository.deleteById(id);
    }
}
