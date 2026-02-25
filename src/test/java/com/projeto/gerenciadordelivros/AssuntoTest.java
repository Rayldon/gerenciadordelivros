package com.projeto.gerenciadordelivros;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.model.Assunto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssuntoTest {

    @Test
    void deveCriarAssuntoComDescricaoValida() {
        Assunto assunto = new Assunto("Arquitetura de Software");

        assertEquals("Arquitetura de Software", assunto.getDescricao());
    }

    @Test
    void naoDeveCriarAssuntoComDescricaoVazia() {
        assertThrows(RegraNegocioException.class, () ->
                new Assunto("")
        );
    }
}
