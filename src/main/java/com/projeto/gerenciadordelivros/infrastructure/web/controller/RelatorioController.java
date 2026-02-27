package com.projeto.gerenciadordelivros.infrastructure.web.controller;

import com.projeto.gerenciadordelivros.application.usecase.GerarRelatorioAutoresUseCase;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final GerarRelatorioAutoresUseCase gerarRelatorioAutoresUseCase;

    public RelatorioController(GerarRelatorioAutoresUseCase gerarRelatorioAutoresUseCase) {
        this.gerarRelatorioAutoresUseCase = gerarRelatorioAutoresUseCase;
    }

    @GetMapping(value = "/autores", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> relatorioAutores() {
        byte[] arquivo = gerarRelatorioAutoresUseCase.executar();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.inline().filename("relatorio-autores.pdf").build().toString())
                .contentType(MediaType.APPLICATION_PDF)
                .body(arquivo);
    }
}
