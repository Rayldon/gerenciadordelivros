package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
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
class ListarLivrosUseCaseTest {

    @Mock
    LivroRepository livroRepository;

    @InjectMocks
    ListarLivrosUseCase useCase;

    @Test
    void deveListarLivrosComSucesso() {
        List<Livro> livros = List.of(mock(Livro.class), mock(Livro.class));
        when(livroRepository.listarTodos()).thenReturn(livros);

        List<Livro> resultado = useCase.executar();

        assertEquals(2, resultado.size());
        verify(livroRepository).listarTodos();
    }
}
