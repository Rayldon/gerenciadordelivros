package com.projeto.gerenciadordelivros.infrastructure.web.controller;

import com.projeto.gerenciadordelivros.application.usecase.CriarLivroUseCase;
import com.projeto.gerenciadordelivros.application.usecase.ListarLivrosUseCase;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.LivroRequest;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.LivroResponse;
import com.projeto.gerenciadordelivros.infrastructure.web.mapper.LivroWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final CriarLivroUseCase criarLivroUseCase;
    private final ListarLivrosUseCase listarLivrosUseCase;
    private final LivroWebMapper mapper;

    public LivroController(CriarLivroUseCase criarLivroUseCase,
                           ListarLivrosUseCase listarLivrosUseCase,
                           LivroWebMapper mapper) {
        this.criarLivroUseCase = criarLivroUseCase;
        this.listarLivrosUseCase = listarLivrosUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponse criar(@RequestBody LivroRequest request) {
        return mapper.toResponse(criarLivroUseCase.executar(mapper.toDomain(request)));
    }

    @GetMapping
    public List<LivroResponse> listar() {
        return listarLivrosUseCase.executar().stream()
                .map(mapper::toResponse)
                .toList();
    }
}
