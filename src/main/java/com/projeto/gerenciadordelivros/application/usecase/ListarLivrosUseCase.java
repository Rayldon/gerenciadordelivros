package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarLivrosUseCase {

    private final LivroRepository livroRepository;

    public ListarLivrosUseCase(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> executar() {
        return livroRepository.listarTodos();
    }
}
