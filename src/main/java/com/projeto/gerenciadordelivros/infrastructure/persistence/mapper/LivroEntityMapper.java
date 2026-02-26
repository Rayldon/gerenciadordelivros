package com.projeto.gerenciadordelivros.infrastructure.persistence.mapper;

import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AssuntoEntity;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AutorEntity;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.LivroEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LivroEntityMapper {

    public LivroEntity toEntity(Livro livro) {
        LivroEntity entity = new LivroEntity();
        entity.setId(livro.getId());
        entity.setTitulo(livro.getTitulo());
        entity.setValor(livro.getValor());
        entity.setAutores(toAutorEntitySet(livro.getAutores()));
        entity.setAssuntos(toAssuntoEntitySet(livro.getAssuntos()));
        return entity;
    }

    public Livro toDomain(LivroEntity entity) {
        Set<Autor> autores = entity.getAutores().stream()
                .map(autorEntity -> new Autor(autorEntity.getNome()))
                .collect(Collectors.toSet());

        Set<Assunto> assuntos = entity.getAssuntos().stream()
                .map(assuntoEntity -> new Assunto(assuntoEntity.getDescricao()))
                .collect(Collectors.toSet());

        return new Livro(entity.getTitulo(), entity.getValor(), autores, assuntos);
    }

    private Set<AutorEntity> toAutorEntitySet(Set<Autor> autores) {
        return autores.stream()
                .map(autor -> {
                    AutorEntity entity = new AutorEntity();
                    entity.setNome(autor.getNome());
                    return entity;
                })
                .collect(Collectors.toSet());
    }

    private Set<AssuntoEntity> toAssuntoEntitySet(Set<Assunto> assuntos) {
        return assuntos.stream()
                .map(assunto -> {
                    AssuntoEntity entity = new AssuntoEntity();
                    entity.setDescricao(assunto.getDescricao());
                    return entity;
                })
                .collect(Collectors.toSet());
    }
}
