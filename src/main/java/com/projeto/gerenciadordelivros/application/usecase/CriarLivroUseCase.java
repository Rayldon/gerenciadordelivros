package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarLivroUseCase {

    private final LivroRepository livroRepository;

    public Livro executar(Livro livro) {
        return livroRepository.salvar(livro);
    }
}
