package com.projeto.gerenciadordelivros.domain.port;

import com.projeto.gerenciadordelivros.domain.model.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroRepository {

    Livro salvar(Livro livro);

    Optional<Livro> buscarPorId(Long id);

    List<Livro> listarTodos();
}
