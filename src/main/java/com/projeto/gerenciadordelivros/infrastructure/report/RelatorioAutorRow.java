package com.projeto.gerenciadordelivros.infrastructure.report;

import java.math.BigDecimal;

public class RelatorioAutorRow {

    private final String autorNome;
    private final String livroTitulo;
    private final BigDecimal livroValor;
    private final String assuntoDescricao;

    public RelatorioAutorRow(String autorNome,
                             String livroTitulo,
                             BigDecimal livroValor,
                             String assuntoDescricao) {
        this.autorNome = autorNome;
        this.livroTitulo = livroTitulo;
        this.livroValor = livroValor;
        this.assuntoDescricao = assuntoDescricao;
    }

    public String getAutorNome() {
        return autorNome;
    }

    public String getLivroTitulo() {
        return livroTitulo;
    }

    public BigDecimal getLivroValor() {
        return livroValor;
    }

    public String getAssuntoDescricao() {
        return assuntoDescricao;
    }
}
