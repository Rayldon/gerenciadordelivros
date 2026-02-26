package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.port.AssuntoRepository;

public class CriarAssuntoUseCase {

    private final AssuntoRepository assuntoRepository;

    public CriarAssuntoUseCase(AssuntoRepository assuntoRepository) {
        this.assuntoRepository = assuntoRepository;
    }

    public Assunto executar(Assunto assunto) {
        return assuntoRepository.salvar(assunto);
    }
}
