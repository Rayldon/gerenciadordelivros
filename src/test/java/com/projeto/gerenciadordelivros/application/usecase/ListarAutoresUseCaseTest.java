package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.domain.port.AutorRepository;
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
class ListarAutoresUseCaseTest {

    @Mock
    AutorRepository autorRepository;

    @InjectMocks
    ListarAutoresUseCase useCase;

    @Test
    void deveListarAutoresComSucesso() {
        List<Autor> autores = List.of(mock(Autor.class), mock(Autor.class));
        when(autorRepository.listarTodos()).thenReturn(autores);

        List<Autor> resultado = useCase.executar();

        assertEquals(2, resultado.size());
        verify(autorRepository).listarTodos();
    }
}
