package com.projeto.gerenciadordelivros.infrastructure.persistence.mapper;

import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AssuntoEntity;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AutorEntity;
import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.LivroEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(LivroEntityMapper.class)
class LivroEntityMapperTest {

    @Autowired
    private LivroEntityMapper mapper;

    @Test
    void deveMapearDomainParaEntity() {
        Livro livro = new Livro(
                "Clean Code",
                new BigDecimal("120.00"),
                Set.of(new Autor("Robert C. Martin")),
                Set.of(new Assunto("Arquitetura"))
        );

        LivroEntity entity = mapper.toEntity(livro);

        assertEquals("Clean Code", entity.getTitulo());
        assertEquals(new BigDecimal("120.00"), entity.getValor());
        assertEquals(1, entity.getAutores().size());
        assertEquals(1, entity.getAssuntos().size());
    }

    @Test
    void deveMapearEntityParaDomain() {
        AutorEntity autor = new AutorEntity();
        autor.setNome("Robert C. Martin");

        AssuntoEntity assunto = new AssuntoEntity();
        assunto.setDescricao("Arquitetura");

        LivroEntity entity = new LivroEntity();
        entity.setTitulo("Clean Code");
        entity.setValor(new BigDecimal("120.00"));
        entity.setAutores(Set.of(autor));
        entity.setAssuntos(Set.of(assunto));

        Livro livro = mapper.toDomain(entity);

        assertEquals("Clean Code", livro.getTitulo());
        assertEquals(new BigDecimal("120.00"), livro.getValor());
        assertEquals(1, livro.getAutores().size());
        assertEquals(1, livro.getAssuntos().size());
    }
}
