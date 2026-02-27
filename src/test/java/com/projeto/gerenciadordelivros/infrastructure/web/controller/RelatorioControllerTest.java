package com.projeto.gerenciadordelivros.infrastructure.web.controller;

import com.projeto.gerenciadordelivros.application.usecase.GerarRelatorioAutoresUseCase;
import com.projeto.gerenciadordelivros.infrastructure.web.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RelatorioController.class)
@Import(GlobalExceptionHandler.class)
class RelatorioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GerarRelatorioAutoresUseCase gerarRelatorioAutoresUseCase;

    @Test
    void deveGerarRelatorioPdfComSucesso() throws Exception {
        byte[] pdf = "%PDF-1.4 fake".getBytes();
        when(gerarRelatorioAutoresUseCase.executar()).thenReturn(pdf);

        mockMvc.perform(get("/relatorios/autores"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_PDF))
                .andExpect(header().string("Content-Disposition", containsString("relatorio-autores.pdf")))
                .andExpect(content().bytes(pdf));
    }
}
