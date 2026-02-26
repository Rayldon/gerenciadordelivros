package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.domain.port.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarAutorUseCase {

    private final AutorRepository autorRepository;

    public CriarAutorUseCase(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor executar(Autor autor) {
        return autorRepository.salvar(autor);
    }
}
