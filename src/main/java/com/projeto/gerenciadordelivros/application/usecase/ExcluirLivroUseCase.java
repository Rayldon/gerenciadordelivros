package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class ExcluirLivroUseCase {

    private final LivroRepository livroRepository;

    public ExcluirLivroUseCase(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void executar(Long id) {
        if (!livroRepository.existePorId(id)) {
            throw new RegraNegocioException("Livro nao encontrado.");
        }
        livroRepository.deletarPorId(id);
    }
}
