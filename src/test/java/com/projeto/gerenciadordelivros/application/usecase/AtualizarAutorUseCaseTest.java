package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.domain.port.AutorRepository;
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
class AtualizarAutorUseCaseTest {

    @Mock
    AutorRepository autorRepository;

    @InjectMocks
    AtualizarAutorUseCase useCase;

    @Test
    void deveAtualizarAutorComSucesso() {
        Autor autor = new Autor("Machado de Assis");
        when(autorRepository.atualizar(1L, autor)).thenReturn(Optional.of(autor));

        Autor resultado = useCase.executar(1L, autor);

        assertEquals(autor, resultado);
        verify(autorRepository).atualizar(1L, autor);
    }

    @Test
    void deveLancarExcecaoQuandoAutorNaoForEncontrado() {
        Autor autor = new Autor("Machado de Assis");
        when(autorRepository.atualizar(1L, autor)).thenReturn(Optional.empty());

        RegraNegocioException ex = assertThrows(RegraNegocioException.class, () -> useCase.executar(1L, autor));
        assertEquals("Autor nao encontrado.", ex.getMessage());
    }
}
