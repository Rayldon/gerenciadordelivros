package com.projeto.gerenciadordelivros.infrastructure.web.controller;

import com.projeto.gerenciadordelivros.application.usecase.AtualizarAssuntoUseCase;
import com.projeto.gerenciadordelivros.application.usecase.CriarAssuntoUseCase;
import com.projeto.gerenciadordelivros.application.usecase.ExcluirAssuntoUseCase;
import com.projeto.gerenciadordelivros.application.usecase.ListarAssuntosUseCase;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.AssuntoRequest;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.AssuntoResponse;
import com.projeto.gerenciadordelivros.infrastructure.web.mapper.AssuntoWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assuntos")
public class AssuntoController {

    private final CriarAssuntoUseCase criarAssuntoUseCase;
    private final AtualizarAssuntoUseCase atualizarAssuntoUseCase;
    private final ExcluirAssuntoUseCase excluirAssuntoUseCase;
    private final ListarAssuntosUseCase listarAssuntosUseCase;
    private final AssuntoWebMapper mapper;

    public AssuntoController(CriarAssuntoUseCase criarAssuntoUseCase,
                             AtualizarAssuntoUseCase atualizarAssuntoUseCase,
                             ExcluirAssuntoUseCase excluirAssuntoUseCase,
                             ListarAssuntosUseCase listarAssuntosUseCase,
                             AssuntoWebMapper mapper) {
        this.criarAssuntoUseCase = criarAssuntoUseCase;
        this.atualizarAssuntoUseCase = atualizarAssuntoUseCase;
        this.excluirAssuntoUseCase = excluirAssuntoUseCase;
        this.listarAssuntosUseCase = listarAssuntosUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssuntoResponse criar(@RequestBody AssuntoRequest request) {
        return mapper.toResponse(criarAssuntoUseCase.executar(mapper.toDomain(request)));
    }

    @GetMapping
    public List<AssuntoResponse> listar() {
        return listarAssuntosUseCase.executar().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @PutMapping("/{id}")
    public AssuntoResponse atualizar(@PathVariable Long id, @RequestBody AssuntoRequest request) {
        return mapper.toResponse(atualizarAssuntoUseCase.executar(id, mapper.toDomain(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        excluirAssuntoUseCase.executar(id);
    }
}
