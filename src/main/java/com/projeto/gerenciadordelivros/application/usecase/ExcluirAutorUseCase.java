package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.port.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirAutorUseCase {

    private final AutorRepository autorRepository;

    public void executar(Long id) {
        if (!autorRepository.existePorId(id)) {
            throw new RegraNegocioException("Autor nao encontrado.");
        }
        if (autorRepository.possuiLivrosVinculados(id)) {
            throw new RegraNegocioException("Autor possui livros vinculados e nao pode ser removido.");
        }
        autorRepository.deletarPorId(id);
    }
}
