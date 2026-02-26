package com.projeto.gerenciadordelivros.infrastructure.web.mapper;

import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.AssuntoRequest;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.AssuntoResponse;
import org.springframework.stereotype.Component;

@Component
public class AssuntoWebMapper {

    public Assunto toDomain(AssuntoRequest request) {
        return new Assunto(request.descricao());
    }

    public AssuntoResponse toResponse(Assunto assunto) {
        return new AssuntoResponse(assunto.getDescricao());
    }
}
