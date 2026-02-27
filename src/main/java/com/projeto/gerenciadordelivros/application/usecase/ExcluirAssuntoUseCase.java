package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.port.AssuntoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirAssuntoUseCase {

    private final AssuntoRepository assuntoRepository;

    public void executar(Long id) {
        if (!assuntoRepository.existePorId(id)) {
            throw new RegraNegocioException("Assunto nao encontrado.");
        }
        if (assuntoRepository.possuiLivrosVinculados(id)) {
            throw new RegraNegocioException("Assunto possui livros vinculados e nao pode ser removido.");
        }
        assuntoRepository.deletarPorId(id);
    }
}
