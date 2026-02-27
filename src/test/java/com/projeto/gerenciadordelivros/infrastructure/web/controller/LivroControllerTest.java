package com.projeto.gerenciadordelivros.infrastructure.web.controller;

import com.projeto.gerenciadordelivros.application.usecase.AtualizarLivroUseCase;
import com.projeto.gerenciadordelivros.application.usecase.CriarLivroUseCase;
import com.projeto.gerenciadordelivros.application.usecase.ExcluirLivroUseCase;
import com.projeto.gerenciadordelivros.application.usecase.ListarLivrosUseCase;
import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.domain.port.Paginacao;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.LivroResponse;
import com.projeto.gerenciadordelivros.infrastructure.web.exception.GlobalExceptionHandler;
import com.projeto.gerenciadordelivros.infrastructure.web.mapper.LivroWebMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LivroController.class)
@Import(GlobalExceptionHandler.class)
class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CriarLivroUseCase criarLivroUseCase;

    @MockitoBean
    private ListarLivrosUseCase listarLivrosUseCase;

    @MockitoBean
    private AtualizarLivroUseCase atualizarLivroUseCase;

    @MockitoBean
    private ExcluirLivroUseCase excluirLivroUseCase;

    @MockitoBean
    private LivroWebMapper mapper;

    @Test
    void deveCriarLivroComSucesso() throws Exception {
        Livro domain = novoLivroValido();
        LivroResponse response = new LivroResponse(
                "Clean Code",
                new BigDecimal("120.00"),
                Set.of("Robert C. Martin"),
                Set.of("Arquitetura")
        );

        when(mapper.toDomain(any())).thenReturn(domain);
        when(criarLivroUseCase.executar(domain)).thenReturn(domain);
        when(mapper.toResponse(domain)).thenReturn(response);

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "titulo": "Clean Code",
                                  "valor": 120.00,
                                  "autores": ["Robert C. Martin"],
                                  "assuntos": ["Arquitetura"]
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Clean Code"))
                .andExpect(jsonPath("$.valor").value(120.00));
    }

    @Test
    void deveListarLivrosComSucesso() throws Exception {
        Livro livro = novoLivroValido();
        LivroResponse response = new LivroResponse(
                "Clean Code",
                new BigDecimal("120.00"),
                Set.of("Robert C. Martin"),
                Set.of("Arquitetura")
        );
        Paginacao<Livro> paginacao = new Paginacao<>(List.of(livro), 0, 10, 1, 1, true, true);

        when(listarLivrosUseCase.executar(0, 10)).thenReturn(paginacao);
        when(mapper.toResponse(livro)).thenReturn(response);

        mockMvc.perform(get("/livros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].titulo").value("Clean Code"))
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    void deveRetornarBadRequestQuandoRegraNegocioFalhar() throws Exception {
        Livro domain = novoLivroValido();

        when(mapper.toDomain(any())).thenReturn(domain);
        when(criarLivroUseCase.executar(domain)).thenThrow(new RegraNegocioException("Titulo do livro e obrigatorio."));

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "titulo": "",
                                  "valor": 120.00,
                                  "autores": ["Robert C. Martin"],
                                  "assuntos": ["Arquitetura"]
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Titulo do livro e obrigatorio."))
                .andExpect(jsonPath("$.path").value("/livros"));
    }

    @Test
    void deveRetornarBadRequestQuandoJsonForInvalido() throws Exception {
        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Corpo da requisicao invalido."))
                .andExpect(jsonPath("$.path").value("/livros"));
    }

    private Livro novoLivroValido() {
        return new Livro(
                "Clean Code",
                new BigDecimal("120.00"),
                Set.of(new Autor("Robert C. Martin")),
                Set.of(new Assunto("Arquitetura"))
        );
    }

    @Test
    void deveAtualizarLivroComSucesso() throws Exception {
        Livro domain = novoLivroValido();
        LivroResponse response = new LivroResponse(
                "Clean Code",
                new BigDecimal("120.00"),
                Set.of("Robert C. Martin"),
                Set.of("Arquitetura")
        );

        when(mapper.toDomain(any())).thenReturn(domain);
        when(atualizarLivroUseCase.executar(eq(1L), eq(domain))).thenReturn(domain);
        when(mapper.toResponse(domain)).thenReturn(response);

        mockMvc.perform(put("/livros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "titulo": "Clean Code",
                                  "valor": 120.00,
                                  "autores": ["Robert C. Martin"],
                                  "assuntos": ["Arquitetura"]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Clean Code"))
                .andExpect(jsonPath("$.valor").value(120.00));
    }

    @Test
    void deveExcluirLivroComSucesso() throws Exception {
        doNothing().when(excluirLivroUseCase).executar(1L);

        mockMvc.perform(delete("/livros/1"))
                .andExpect(status().isNoContent());
    }
}
