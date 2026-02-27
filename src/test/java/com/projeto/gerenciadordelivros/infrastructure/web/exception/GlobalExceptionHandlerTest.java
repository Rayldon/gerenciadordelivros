package com.projeto.gerenciadordelivros.infrastructure.web.exception;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void deveRetornarBadRequestParaRegraNegocioException() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/livros");

        ResponseEntity<ErrorResponse> response = handler.handleRegraNegocioException(
                new RegraNegocioException("Titulo do livro e obrigatorio."),
                request
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getBody().status());
        assertEquals("Titulo do livro e obrigatorio.", response.getBody().message());
        assertEquals("/livros", response.getBody().path());
    }
}
