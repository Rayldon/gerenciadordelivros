package com.projeto.gerenciadordelivros.infrastructure.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class RelatorioAutorRow {

    private final String autorNome;
    private final String livroTitulo;
    private final BigDecimal livroValor;
    private final String assuntoDescricao;
}
