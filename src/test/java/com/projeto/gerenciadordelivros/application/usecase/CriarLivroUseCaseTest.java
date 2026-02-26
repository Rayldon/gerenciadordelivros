package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
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
class CriarLivroUseCaseTest {

    @Mock
    LivroRepository livroRepository;

    @InjectMocks
    CriarLivroUseCase useCase;

    @Test
    void deveSalvarLivroComSucesso() {
        Livro livro = mock(Livro.class);
        when(livroRepository.salvar(livro)).thenReturn(livro);

        Livro resultado = useCase.executar(livro);

        assertEquals(livro, resultado);
        verify(livroRepository).salvar(livro);
    }
}
