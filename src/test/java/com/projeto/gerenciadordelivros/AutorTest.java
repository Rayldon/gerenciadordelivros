package com.projeto.gerenciadordelivros;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.model.Autor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AutorTest {

    @Test
    void deveCriarAutorComNomeValido() {
        Autor autor = new Autor("Machado de Assis");

        assertEquals("Machado de Assis", autor.getNome());
    }

    @Test
    void naoDeveCriarAutorComNomeVazio() {
        assertThrows(RegraNegocioException.class, () ->
                new Autor("")
        );
    }

    @Test
    void naoDeveCriarAutorComNomeEmBranco() {
        assertThrows(RegraNegocioException.class, () ->
                new Autor("   ")
        );
    }
}

