package com.projeto.gerenciadordelivros.infrastructure.web.controller;

import com.projeto.gerenciadordelivros.application.usecase.CriarAutorUseCase;
import com.projeto.gerenciadordelivros.application.usecase.ListarAutoresUseCase;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.AutorRequest;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.AutorResponse;
import com.projeto.gerenciadordelivros.infrastructure.web.mapper.AutorWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final CriarAutorUseCase criarAutorUseCase;
    private final ListarAutoresUseCase listarAutoresUseCase;
    private final AutorWebMapper mapper;

    public AutorController(CriarAutorUseCase criarAutorUseCase,
                           ListarAutoresUseCase listarAutoresUseCase,
                           AutorWebMapper mapper) {
        this.criarAutorUseCase = criarAutorUseCase;
        this.listarAutoresUseCase = listarAutoresUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutorResponse criar(@RequestBody AutorRequest request) {
        return mapper.toResponse(criarAutorUseCase.executar(mapper.toDomain(request)));
    }

    @GetMapping
    public List<AutorResponse> listar() {
        return listarAutoresUseCase.executar().stream()
                .map(mapper::toResponse)
                .toList();
    }
}
