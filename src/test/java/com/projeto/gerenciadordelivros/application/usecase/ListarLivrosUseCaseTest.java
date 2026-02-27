package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
import com.projeto.gerenciadordelivros.domain.port.Paginacao;
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
    void deveListarLivrosComPaginacaoComSucesso() {
        List<Livro> livros = List.of(mock(Livro.class), mock(Livro.class));
        Paginacao<Livro> pagina = new Paginacao<>(livros, 0, 10, 2, 1, true, true);
        when(livroRepository.listarPaginado(0, 10)).thenReturn(pagina);

        Paginacao<Livro> resultado = useCase.executar(0, 10);

        assertEquals(2, resultado.itens().size());
        assertEquals(2, resultado.totalItens());
        verify(livroRepository).listarPaginado(0, 10);
    }
}
