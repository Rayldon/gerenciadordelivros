package com.projeto.gerenciadordelivros.domain.model;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;

public class Assunto {

    private Long id;
    private String descricao;

    public Assunto(String descricao) {
        validarDescricao(descricao);
        this.descricao = descricao;
    }

    public Assunto(Long id, String descricao) {
        validarDescricao(descricao);
        this.id = id;
        this.descricao = descricao;
    }

    private void validarDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new RegraNegocioException("Descricao do assunto e obrigatoria.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
