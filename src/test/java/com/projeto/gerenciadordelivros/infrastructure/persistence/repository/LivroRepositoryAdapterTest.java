package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.infrastructure.persistence.mapper.LivroEntityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import({LivroRepositoryAdapter.class, LivroEntityMapper.class})
class LivroRepositoryAdapterTest {

    @Autowired
    private LivroRepositoryAdapter adapter;

    @Autowired
    private LivroJpaRepository livroJpaRepository;

    @Test
    void deveSalvarLivroComSucesso() {
        Livro livro = new Livro(
                "Clean Code",
                new BigDecimal("120.00"),
                Set.of(new Autor("Robert C. Martin")),
                Set.of(new Assunto("Arquitetura"))
        );

        Livro salvo = adapter.salvar(livro);

        assertEquals("Clean Code", salvo.getTitulo());
        assertEquals(new BigDecimal("120.00"), salvo.getValor());
        assertEquals(1, livroJpaRepository.count());
    }

    @Test
    @Sql("classpath:scripts/test-data.sql")
    void deveBuscarLivroPorId() {
        Long id = livroJpaRepository.findAll()
                .stream()
                .filter(livro -> "Domain-Driven Design".equals(livro.getTitulo()))
                .findFirst()
                .map(livro -> livro.getId())
                .orElse(null);

        assertNotNull(id);

        var buscado = adapter.buscarPorId(id);

        assertTrue(buscado.isPresent());
        assertEquals("Domain-Driven Design", buscado.get().getTitulo());
    }

    @Test
    @Sql("classpath:scripts/test-data.sql")
    void deveListarLivrosPaginados() {
        var pagina = adapter.listarPaginado(0, 10);

        assertEquals(2, pagina.itens().size());
        assertEquals(2, pagina.totalItens());
        assertEquals(1, pagina.totalPaginas());
    }
}
