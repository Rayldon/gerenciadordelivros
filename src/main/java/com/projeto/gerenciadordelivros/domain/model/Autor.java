package com.projeto.gerenciadordelivros.domain.model;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;

public class Autor {

    private String nome;

    public Autor(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new RegraNegocioException("Nome do autor e obrigatorio.");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

