package com.projeto.gerenciadordelivros.infrastructure.web.mapper;

import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.AutorRequest;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.AutorResponse;
import org.springframework.stereotype.Component;

@Component
public class AutorWebMapper {

    public Autor toDomain(AutorRequest request) {
        return new Autor(request.nome());
    }

    public AutorResponse toResponse(Autor autor) {
        return new AutorResponse(autor.getNome());
    }
}
