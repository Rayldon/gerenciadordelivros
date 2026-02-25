package com.projeto.gerenciadordelivros.domain.exception;

public class RegraNegocioException extends RuntimeException {

    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
