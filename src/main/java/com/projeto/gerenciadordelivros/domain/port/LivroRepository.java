package com.projeto.gerenciadordelivros.domain.port;

import com.projeto.gerenciadordelivros.domain.model.Livro;

import java.util.Optional;

public interface LivroRepository {

    Livro salvar(Livro livro);

    Optional<Livro> buscarPorId(Long id);

    Paginacao<Livro> listarPaginado(int pagina, int tamanho);

    Optional<Livro> atualizar(Long id, Livro livro);

    boolean existePorId(Long id);

    void deletarPorId(Long id);
}
