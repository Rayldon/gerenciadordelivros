package com.projeto.gerenciadordelivros.infrastructure.web.controller;

import com.projeto.gerenciadordelivros.application.usecase.AtualizarLivroUseCase;
import com.projeto.gerenciadordelivros.application.usecase.CriarLivroUseCase;
import com.projeto.gerenciadordelivros.application.usecase.ExcluirLivroUseCase;
import com.projeto.gerenciadordelivros.application.usecase.ListarLivrosUseCase;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.LivroRequest;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.LivroResponse;
import com.projeto.gerenciadordelivros.infrastructure.web.dto.PageResponse;
import com.projeto.gerenciadordelivros.infrastructure.web.mapper.LivroWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final CriarLivroUseCase criarLivroUseCase;
    private final AtualizarLivroUseCase atualizarLivroUseCase;
    private final ExcluirLivroUseCase excluirLivroUseCase;
    private final ListarLivrosUseCase listarLivrosUseCase;
    private final LivroWebMapper mapper;

    public LivroController(CriarLivroUseCase criarLivroUseCase,
                           AtualizarLivroUseCase atualizarLivroUseCase,
                           ExcluirLivroUseCase excluirLivroUseCase,
                           ListarLivrosUseCase listarLivrosUseCase,
                           LivroWebMapper mapper) {
        this.criarLivroUseCase = criarLivroUseCase;
        this.atualizarLivroUseCase = atualizarLivroUseCase;
        this.excluirLivroUseCase = excluirLivroUseCase;
        this.listarLivrosUseCase = listarLivrosUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponse criar(@RequestBody LivroRequest request) {
        return mapper.toResponse(criarLivroUseCase.executar(mapper.toDomain(request)));
    }

    @GetMapping
    public PageResponse<LivroResponse> listar(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        var resultado = listarLivrosUseCase.executar(page, size);

        var content = resultado.itens().stream()
                .map(mapper::toResponse)
                .toList();

        return new PageResponse<>(
                content,
                resultado.pagina(),
                resultado.tamanho(),
                resultado.totalItens(),
                resultado.totalPaginas(),
                resultado.primeira(),
                resultado.ultima()
        );
    }

    @PutMapping("/{id}")
    public LivroResponse atualizar(@PathVariable Long id, @RequestBody LivroRequest request) {
        return mapper.toResponse(atualizarLivroUseCase.executar(id, mapper.toDomain(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        excluirLivroUseCase.executar(id);
    }
}
