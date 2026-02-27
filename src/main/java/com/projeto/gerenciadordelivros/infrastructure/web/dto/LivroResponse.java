package com.projeto.gerenciadordelivros.infrastructure.web.dto;

import java.math.BigDecimal;
import java.util.Set;

public record LivroResponse(
        Long id,
        String titulo,
        BigDecimal valor,
        Set<String> autores,
        Set<String> assuntos
) {
}
