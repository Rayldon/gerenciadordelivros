package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirLivroUseCase {

    private final LivroRepository livroRepository;

    public void executar(Long id) {
        if (!livroRepository.existePorId(id)) {
            throw new RegraNegocioException("Livro nao encontrado.");
        }
        livroRepository.deletarPorId(id);
    }
}
