package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.port.AssuntoRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarAssuntoUseCase {

    private final AssuntoRepository assuntoRepository;

    public AtualizarAssuntoUseCase(AssuntoRepository assuntoRepository) {
        this.assuntoRepository = assuntoRepository;
    }

    public Assunto executar(Long id, Assunto assunto) {
        return assuntoRepository.atualizar(id, assunto)
                .orElseThrow(() -> new RegraNegocioException("Assunto nao encontrado."));
    }
}
