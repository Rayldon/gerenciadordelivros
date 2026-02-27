package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
import com.projeto.gerenciadordelivros.domain.port.Paginacao;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AssuntoEntity;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AutorEntity;
import com.projeto.gerenciadordelivros.infrastructure.persistence.mapper.LivroEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class LivroRepositoryAdapter implements LivroRepository {

    private final LivroJpaRepository livroJpaRepository;
    private final AutorJpaRepository autorJpaRepository;
    private final AssuntoJpaRepository assuntoJpaRepository;
    private final LivroEntityMapper livroEntityMapper;

    @Override
    public Livro salvar(Livro livro) {
        var entity = livroEntityMapper.toEntity(livro);
        entity.setAutores(resolverAutores(entity.getAutores()));
        entity.setAssuntos(resolverAssuntos(entity.getAssuntos()));

        return livroEntityMapper.toDomain(livroJpaRepository.save(entity));
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
                    entity.setAutores(resolverAutores(atualizado.getAutores()));
                    entity.setAssuntos(resolverAssuntos(atualizado.getAssuntos()));
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

    private Set<AutorEntity> resolverAutores(Set<AutorEntity> autores) {
        var resolvidos = new LinkedHashSet<AutorEntity>();

        for (AutorEntity autor : autores) {
            var nome = autor.getNome() == null ? null : autor.getNome().trim();

            var resolved = autorJpaRepository.findByNomeIgnoreCase(nome)
                    .orElseGet(() -> {
                        AutorEntity novo = new AutorEntity();
                        novo.setNome(nome);
                        return autorJpaRepository.save(novo);
                    });

            resolvidos.add(resolved);
        }

        return resolvidos;
    }

    private Set<AssuntoEntity> resolverAssuntos(Set<AssuntoEntity> assuntos) {
        var resolvidos = new LinkedHashSet<AssuntoEntity>();

        for (AssuntoEntity assunto : assuntos) {
            var descricao = assunto.getDescricao() == null ? null : assunto.getDescricao().trim();

            var resolved = assuntoJpaRepository.findByDescricaoIgnoreCase(descricao)
                    .orElseGet(() -> {
                        AssuntoEntity novo = new AssuntoEntity();
                        novo.setDescricao(descricao);
                        return assuntoJpaRepository.save(novo);
                    });

            resolvidos.add(resolved);
        }

        return resolvidos;
    }
}
