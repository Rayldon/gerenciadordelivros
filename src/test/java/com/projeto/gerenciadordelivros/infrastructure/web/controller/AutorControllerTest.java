package com.projeto.gerenciadordelivros.infrastructure.web.controller;

import com.projeto.gerenciadordelivros.application.usecase.CriarAutorUseCase;
import com.projeto.gerenciadordelivros.application.usecase.ListarAutoresUseCase;
import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.AutorResponse;
import com.projeto.gerenciadordelivros.infrastructure.web.exception.GlobalExceptionHandler;
import com.projeto.gerenciadordelivros.infrastructure.web.mapper.AutorWebMapper;
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

@WebMvcTest(AutorController.class)
@Import(GlobalExceptionHandler.class)
class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CriarAutorUseCase criarAutorUseCase;

    @MockitoBean
    private ListarAutoresUseCase listarAutoresUseCase;

    @MockitoBean
    private AutorWebMapper mapper;

    @Test
    void deveCriarAutorComSucesso() throws Exception {
        Autor domain = new Autor("Machado de Assis");
        AutorResponse response = new AutorResponse("Machado de Assis");

        when(mapper.toDomain(any())).thenReturn(domain);
        when(criarAutorUseCase.executar(domain)).thenReturn(domain);
        when(mapper.toResponse(domain)).thenReturn(response);

        mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Machado de Assis\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Machado de Assis"));
    }

    @Test
    void deveListarAutoresComSucesso() throws Exception {
        Autor domain = new Autor("Machado de Assis");
        AutorResponse response = new AutorResponse("Machado de Assis");

        when(listarAutoresUseCase.executar()).thenReturn(List.of(domain));
        when(mapper.toResponse(domain)).thenReturn(response);

        mockMvc.perform(get("/autores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Machado de Assis"));
    }
}
