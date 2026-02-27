package com.projeto.gerenciadordelivros.domain.port;

import com.projeto.gerenciadordelivros.domain.model.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorRepository {

    Autor salvar(Autor autor);

    List<Autor> listarTodos();

    Optional<Autor> buscarPorId(Long id);

    Optional<Autor> atualizar(Long id, Autor autor);

    boolean existePorId(Long id);

    void deletarPorId(Long id);

    boolean possuiLivrosVinculados(Long id);
}
