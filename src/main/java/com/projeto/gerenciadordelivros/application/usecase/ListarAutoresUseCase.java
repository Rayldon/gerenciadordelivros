package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.domain.port.AutorRepository;

import java.util.List;

public class ListarAutoresUseCase {

    private final AutorRepository autorRepository;

    public ListarAutoresUseCase(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> executar() {
        return autorRepository.listarTodos();
    }
}
