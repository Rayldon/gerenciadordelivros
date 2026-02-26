package com.projeto.gerenciadordelivros.infrastructure.web.mapper;

import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.LivroRequest;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.LivroResponse;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LivroWebMapper {

    public Livro toDomain(LivroRequest request) {
        Set<Autor> autores = request.autores() == null ? null : request.autores().stream()
                .map(Autor::new)
                .collect(Collectors.toSet());

        Set<Assunto> assuntos = request.assuntos() == null ? null : request.assuntos().stream()
                .map(Assunto::new)
                .collect(Collectors.toSet());

        return new Livro(request.titulo(), request.valor(), autores, assuntos);
    }

    public LivroResponse toResponse(Livro livro) {
        Set<String> autores = livro.getAutores().stream()
                .map(Autor::getNome)
                .collect(Collectors.toSet());

        Set<String> assuntos = livro.getAssuntos().stream()
                .map(Assunto::getDescricao)
                .collect(Collectors.toSet());

        return new LivroResponse(livro.getTitulo(), livro.getValor(), autores, assuntos);
    }
}
