package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.port.AssuntoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExcluirAssuntoUseCaseTest {

    @Mock
    AssuntoRepository assuntoRepository;

    @InjectMocks
    ExcluirAssuntoUseCase useCase;

    @Test
    void deveExcluirAssuntoComSucesso() {
        when(assuntoRepository.existePorId(1L)).thenReturn(true);
        when(assuntoRepository.possuiLivrosVinculados(1L)).thenReturn(false);

        useCase.executar(1L);

        verify(assuntoRepository).deletarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoAssuntoNaoExiste() {
        when(assuntoRepository.existePorId(1L)).thenReturn(false);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> useCase.executar(1L));
        assertEquals("Assunto nao encontrado.", ex.getMessage());
        verify(assuntoRepository, never()).deletarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoAssuntoPossuiLivrosVinculados() {
        when(assuntoRepository.existePorId(1L)).thenReturn(true);
        when(assuntoRepository.possuiLivrosVinculados(1L)).thenReturn(true);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> useCase.executar(1L));
        assertEquals("Assunto possui livros vinculados e nao pode ser removido.", ex.getMessage());
        verify(assuntoRepository, never()).deletarPorId(1L);
    }
}
