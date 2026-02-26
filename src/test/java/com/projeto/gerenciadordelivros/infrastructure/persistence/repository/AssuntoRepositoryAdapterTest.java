package com.projeto.gerenciadordelivros.infrastructure.persistence.repository;

import com.projeto.gerenciadordelivros.domain.model.Assunto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(AssuntoRepositoryAdapter.class)
class AssuntoRepositoryAdapterTest {

    @Autowired
    private AssuntoRepositoryAdapter adapter;

    @Autowired
    private AssuntoJpaRepository assuntoJpaRepository;

    @Test
    void deveSalvarAssuntoComSucesso() {
        Assunto salvo = adapter.salvar(new Assunto("Literatura Brasileira"));

        assertEquals("Literatura Brasileira", salvo.getDescricao());
        assertEquals(1, assuntoJpaRepository.count());
    }

    @Test
    void deveListarAssuntos() {
        adapter.salvar(new Assunto("Arquitetura"));
        adapter.salvar(new Assunto("Design"));

        var assuntos = adapter.listarTodos();

        assertEquals(2, assuntos.size());
    }
}
