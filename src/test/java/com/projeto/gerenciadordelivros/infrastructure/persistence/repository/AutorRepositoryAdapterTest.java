package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.domain.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(AutorRepositoryAdapter.class)
class AutorRepositoryAdapterTest {

    @Autowired
    private AutorRepositoryAdapter adapter;

    @Autowired
    private AutorJpaRepository autorJpaRepository;

    @Test
    void deveSalvarAutorComSucesso() {
        Autor salvo = adapter.salvar(new Autor("Machado de Assis"));

        assertEquals("Machado de Assis", salvo.getNome());
        assertEquals(1, autorJpaRepository.count());
    }

    @Test
    void deveListarAutores() {
        adapter.salvar(new Autor("Machado de Assis"));
        adapter.salvar(new Autor("Clarice Lispector"));

        var autores = adapter.listarTodos();

        assertEquals(2, autores.size());
    }
}
