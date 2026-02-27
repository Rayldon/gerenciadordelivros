package com.projeto.gerenciadordelivros.domain.model;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import lombok.Getter;

@Getter
public class Autor {

    private Long id;
    private String nome;

    public Autor(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    public Autor(Long id, String nome) {
        validarNome(nome);
        this.id = id;
        this.nome = nome;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new RegraNegocioException("Nome do autor e obrigatorio.");
        }
    }
}

