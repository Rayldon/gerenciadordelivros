package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.exception.RegraNegocioException;
import com.projeto.gerenciadordelivros.domain.model.Livro;
import com.projeto.gerenciadordelivros.domain.port.Paginacao;
import com.projeto.gerenciadordelivros.domain.port.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class ListarLivrosUseCase {

    private final LivroRepository livroRepository;

    public ListarLivrosUseCase(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Paginacao<Livro> executar(int pagina, int tamanho) {
        if (pagina < 0) {
            throw new RegraNegocioException("Pagina deve ser maior ou igual a zero.");
        }
        if (tamanho <= 0) {
            throw new RegraNegocioException("Tamanho da pagina deve ser maior que zero.");
        }

        return livroRepository.listarPaginado(pagina, tamanho);
    }
}
