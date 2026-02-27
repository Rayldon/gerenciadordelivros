package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizarLivroUseCaseTest {

    @Mock
    LivroRepository livroRepository;

    @InjectMocks
    AtualizarLivroUseCase useCase;

    @Test
    void deveAtualizarLivroComSucesso() {
        Livro livro = mock(Livro.class);
        when(livroRepository.atualizar(1L, livro)).thenReturn(Optional.of(livro));

        Livro resultado = useCase.executar(1L, livro);

        assertEquals(livro, resultado);
        verify(livroRepository).atualizar(1L, livro);
    }

    @Test
    void deveLancarExcecaoQuandoLivroNaoForEncontrado() {
        Livro livro = mock(Livro.class);
        when(livroRepository.atualizar(1L, livro)).thenReturn(Optional.empty());

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> useCase.executar(1L, livro));
        assertEquals("Livro nao encontrado.", ex.getMessage());
    }
}
