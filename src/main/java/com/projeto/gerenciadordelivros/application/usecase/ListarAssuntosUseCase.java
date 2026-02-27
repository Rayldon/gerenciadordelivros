package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Assunto;
import com.projeto.gerenciadordelivros.domain.port.AssuntoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarAssuntosUseCase {

    private final AssuntoRepository assuntoRepository;

    public List<Assunto> executar() {
        return assuntoRepository.listarTodos();
    }
}
