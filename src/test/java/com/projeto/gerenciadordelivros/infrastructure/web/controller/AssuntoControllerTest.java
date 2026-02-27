package com.projeto.gerenciadordelivros.infrastructure.web.controller;

import com.projeto.gerenciadordelivros.application.usecase.CriarAssuntoUseCase;
import com.projeto.gerenciadordelivros.application.usecase.ListarAssuntosUseCase;
import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.AssuntoResponse;
import com.projeto.gerenciadordelivros.infrastructure.web.exception.GlobalExceptionHandler;
import com.projeto.gerenciadordelivros.infrastructure.web.mapper.AssuntoWebMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssuntoController.class)
@Import(GlobalExceptionHandler.class)
class AssuntoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CriarAssuntoUseCase criarAssuntoUseCase;

    @MockitoBean
    private ListarAssuntosUseCase listarAssuntosUseCase;

    @MockitoBean
    private AssuntoWebMapper mapper;

    @Test
    void deveCriarAssuntoComSucesso() throws Exception {
        Assunto domain = new Assunto("Arquitetura");
        AssuntoResponse response = new AssuntoResponse("Arquitetura");

        when(mapper.toDomain(any())).thenReturn(domain);
        when(criarAssuntoUseCase.executar(domain)).thenReturn(domain);
        when(mapper.toResponse(domain)).thenReturn(response);

        mockMvc.perform(post("/assuntos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\":\"Arquitetura\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.descricao").value("Arquitetura"));
    }

    @Test
    void deveListarAssuntosComSucesso() throws Exception {
        Assunto domain = new Assunto("Arquitetura");
        AssuntoResponse response = new AssuntoResponse("Arquitetura");

        when(listarAssuntosUseCase.executar()).thenReturn(List.of(domain));
        when(mapper.toResponse(domain)).thenReturn(response);

        mockMvc.perform(get("/assuntos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].descricao").value("Arquitetura"));
    }

    @Test
    void deveRetornarBadRequestQuandoDescricaoAssuntoForInvalida() throws Exception {
        when(mapper.toDomain(any())).thenThrow(new RegraNegocioException("Descricao do assunto e obrigatoria."));

        mockMvc.perform(post("/assuntos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Descricao do assunto e obrigatoria."))
                .andExpect(jsonPath("$.path").value("/assuntos"));
    }

    @Test
    void deveRetornarBadRequestQuandoJsonForInvalido() throws Exception {
        mockMvc.perform(post("/assuntos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\":"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Corpo da requisicao invalido."))
                .andExpect(jsonPath("$.path").value("/assuntos"));
    }
}
