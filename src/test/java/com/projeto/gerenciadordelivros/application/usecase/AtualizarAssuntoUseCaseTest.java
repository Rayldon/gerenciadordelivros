package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.port.AssuntoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizarAssuntoUseCaseTest {

    @Mock
    AssuntoRepository assuntoRepository;

    @InjectMocks
    AtualizarAssuntoUseCase useCase;

    @Test
    void deveAtualizarAssuntoComSucesso() {
        Assunto assunto = new Assunto("Arquitetura");
        when(assuntoRepository.atualizar(1L, assunto)).thenReturn(Optional.of(assunto));

        Assunto resultado = useCase.executar(1L, assunto);

        assertEquals(assunto, resultado);
        verify(assuntoRepository).atualizar(1L, assunto);
    }

    @Test
    void deveLancarExcecaoQuandoAssuntoNaoForEncontrado() {
        Assunto assunto = new Assunto("Arquitetura");
        when(assuntoRepository.atualizar(1L, assunto)).thenReturn(Optional.empty());

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> useCase.executar(1L, assunto));
        assertEquals("Assunto nao encontrado.", ex.getMessage());
    }
}
