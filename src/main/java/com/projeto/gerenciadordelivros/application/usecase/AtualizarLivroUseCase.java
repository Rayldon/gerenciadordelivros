package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarLivroUseCase {

    private final LivroRepository livroRepository;

    public Livro executar(Long id, Livro livro) {
        return livroRepository.atualizar(id, livro)
                .orElseThrow(() -> new RegraNegocioException("Livro nao encontrado."));
    }
}
