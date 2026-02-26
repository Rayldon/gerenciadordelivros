package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarLivroUseCase {

    private final LivroRepository livroRepository;

    public CriarLivroUseCase(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro executar(Livro livro) {
        return livroRepository.salvar(livro);
    }
}
