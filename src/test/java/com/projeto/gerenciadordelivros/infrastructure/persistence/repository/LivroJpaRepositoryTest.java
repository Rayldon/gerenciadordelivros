package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AssuntoEntity;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AutorEntity;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.LivroEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class LivroJpaRepositoryTest {

    @Autowired
    private LivroJpaRepository livroJpaRepository;

    @Test
    void deveSalvarLivroComRelacionamentos() {
        AutorEntity autor = new AutorEntity();
        autor.setNome("Robert C. Martin");

        AssuntoEntity assunto = new AssuntoEntity();
        assunto.setDescricao("Arquitetura");

        LivroEntity livro = new LivroEntity();
        livro.setTitulo("Clean Code");
        livro.setValor(new BigDecimal("120.00"));
        livro.setAutores(Set.of(autor));
        livro.setAssuntos(Set.of(assunto));

        LivroEntity salvo = livroJpaRepository.save(livro);

        assertNotNull(salvo.getId());
        assertEquals("Clean Code", salvo.getTitulo());
        assertEquals(new BigDecimal("120.00"), salvo.getValor());
        assertEquals(1, salvo.getAutores().size());
        assertEquals(1, salvo.getAssuntos().size());
    }

    @Test
    @Sql("classpath:scripts/test-data.sql")
    void deveConsultarLivroComRelacionamentosPreCadastrado() {
        var livro = livroJpaRepository.findAll()
                .stream()
                .filter(item -> "Clean Code".equals(item.getTitulo()))
                .findFirst()
                .orElse(null);

        assertNotNull(livro);

        var buscado = livroJpaRepository.findById(livro.getId());

        assertTrue(buscado.isPresent());
        assertEquals("Clean Code", buscado.get().getTitulo());
        assertEquals(new BigDecimal("120.00"), buscado.get().getValor());
        assertFalse(buscado.get().getAutores().isEmpty());
        assertFalse(buscado.get().getAssuntos().isEmpty());
    }
}
