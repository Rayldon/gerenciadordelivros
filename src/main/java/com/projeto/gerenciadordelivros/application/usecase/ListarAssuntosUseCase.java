package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.port.AssuntoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAssuntosUseCase {

    private final AssuntoRepository assuntoRepository;

    public ListarAssuntosUseCase(AssuntoRepository assuntoRepository) {
        this.assuntoRepository = assuntoRepository;
    }

    public List<Assunto> executar() {
        return assuntoRepository.listarTodos();
    }
}
