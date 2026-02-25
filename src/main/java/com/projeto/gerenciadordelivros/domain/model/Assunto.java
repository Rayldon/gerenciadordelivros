package com.projeto.gerenciadordelivros.domain.model;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;

public class Assunto {

    private Long id;
    private String descricao;

    public Assunto(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new RegraNegocioException("Descricao do assunto e obrigatoria.");
        }
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
