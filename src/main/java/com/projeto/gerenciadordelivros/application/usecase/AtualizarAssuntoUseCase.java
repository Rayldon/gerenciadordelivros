package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.port.AssuntoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarAssuntoUseCase {

    private final AssuntoRepository assuntoRepository;

    public Assunto executar(Long id, Assunto assunto) {
        return assuntoRepository.atualizar(id, assunto)
                .orElseThrow(() -> new RegraNegocioException("Assunto nao encontrado."));
    }
}
