package com.projeto.gerenciadordelivros.infrastructure.web.dto;

import java.math.BigDecimal;
import java.util.Set;

public record LivroRequest(
        String titulo,
        BigDecimal valor,
        Set<String> autores,
        Set<String> assuntos
) {
}
