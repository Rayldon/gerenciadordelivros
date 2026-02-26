package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.port.AssuntoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarAssuntosUseCaseTest {

    @Mock
    AssuntoRepository assuntoRepository;

    @InjectMocks
    ListarAssuntosUseCase useCase;

    @Test
    void deveListarAssuntosComSucesso() {
        List<Assunto> assuntos = List.of(mock(Assunto.class), mock(Assunto.class));
        when(assuntoRepository.listarTodos()).thenReturn(assuntos);

        List<Assunto> resultado = useCase.executar();

        assertEquals(2, resultado.size());
        verify(assuntoRepository).listarTodos();
    }
}
