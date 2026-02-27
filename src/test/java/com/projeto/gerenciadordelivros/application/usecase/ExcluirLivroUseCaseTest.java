package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
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
class ExcluirLivroUseCaseTest {

    @Mock
    LivroRepository livroRepository;

    @InjectMocks
    ExcluirLivroUseCase useCase;

    @Test
    void deveExcluirLivroComSucesso() {
        when(livroRepository.existePorId(1L)).thenReturn(true);

        useCase.executar(1L);

        verify(livroRepository).deletarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoLivroNaoExiste() {
        when(livroRepository.existePorId(1L)).thenReturn(false);

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> useCase.executar(1L));
        assertEquals("Livro nao encontrado.", ex.getMessage());
        verify(livroRepository, never()).deletarPorId(1L);
    }
}
