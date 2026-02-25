package com.projeto.gerenciadordelivros;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LivroTest {

    @Test
    void deveCriarLivroComValorValido() {
        Livro livro = new Livro(
                "Clean Code",
                new BigDecimal("120.00"),
                Set.of(new Autor("Robert C. Martin"))
        );

        assertEquals(new BigDecimal("120.00"), livro.getValor());
    }

    @Test
    void naoDeveCriarLivroSemAutor() {
        assertThrows(RegraNegocioException.class, () ->
                new Livro("Clean Code", new BigDecimal("120.00"), Set.of())
        );
    }
}
