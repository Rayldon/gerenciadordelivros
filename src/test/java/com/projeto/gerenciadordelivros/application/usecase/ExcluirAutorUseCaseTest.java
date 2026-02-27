package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.port.AutorRepository;
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
class ExcluirAutorUseCaseTest {

    @Mock
    AutorRepository autorRepository;

    @InjectMocks
    ExcluirAutorUseCase useCase;

    @Test
    void deveExcluirAutorComSucesso() {
        when(autorRepository.existePorId(1L)).thenReturn(true);
        when(autorRepository.possuiLivrosVinculados(1L)).thenReturn(false);

        useCase.executar(1L);

        verify(autorRepository).deletarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoAutorNaoExiste() {
        when(autorRepository.existePorId(1L)).thenReturn(false);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> useCase.executar(1L));
        assertEquals("Autor nao encontrado.", ex.getMessage());
        verify(autorRepository, never()).deletarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoAutorPossuiLivrosVinculados() {
        when(autorRepository.existePorId(1L)).thenReturn(true);
        when(autorRepository.possuiLivrosVinculados(1L)).thenReturn(true);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> useCase.executar(1L));
        assertEquals("Autor possui livros vinculados e nao pode ser removido.", ex.getMessage());
        verify(autorRepository, never()).deletarPorId(1L);
    }
}
