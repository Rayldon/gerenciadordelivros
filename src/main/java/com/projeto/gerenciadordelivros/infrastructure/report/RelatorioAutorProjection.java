package com.projeto.gerenciadordelivros.infrastructure.report;

import java.math.BigDecimal;

public interface RelatorioAutorProjection {

    String getAutorNome();

    String getLivroTitulo();

    BigDecimal getLivroValor();

    String getAssuntoDescricao();
}
