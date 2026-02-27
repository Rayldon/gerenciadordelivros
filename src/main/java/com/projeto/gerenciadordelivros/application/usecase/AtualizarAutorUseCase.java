package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.model.Autor;
import com.projeto.gerenciadordelivros.domain.port.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarAutorUseCase {

    private final AutorRepository autorRepository;

    public AtualizarAutorUseCase(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor executar(Long id, Autor autor) {
        return autorRepository.atualizar(id, autor)
                .orElseThrow(() -> new RegraNegocioException("Autor nao encontrado."));
    }
}
