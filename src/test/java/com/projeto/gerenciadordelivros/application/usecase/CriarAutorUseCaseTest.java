package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.domain.port.AutorRepository;
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
class CriarAutorUseCaseTest {

    @Mock
    AutorRepository autorRepository;

    @InjectMocks
    CriarAutorUseCase useCase;

    @Test
    void deveSalvarAutorComSucesso() {
        Autor autor = mock(Autor.class);
        when(autorRepository.salvar(autor)).thenReturn(autor);

        Autor resultado = useCase.executar(autor);

        assertEquals(autor, resultado);
        verify(autorRepository).salvar(autor);
    }
}
