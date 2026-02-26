package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.port.AssuntoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarAssuntoUseCaseTest {

    @Mock
    AssuntoRepository assuntoRepository;

    @InjectMocks
    CriarAssuntoUseCase useCase;

    @Test
    void deveSalvarAssuntoComSucesso() {
        Assunto assunto = mock(Assunto.class);
        when(assuntoRepository.salvar(assunto)).thenReturn(assunto);

        Assunto resultado = useCase.executar(assunto);

        assertEquals(assunto, resultado);
        verify(assuntoRepository).salvar(assunto);
    }
}
