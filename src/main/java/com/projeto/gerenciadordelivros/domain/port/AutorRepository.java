package com.projeto.gerenciadordelivros.domain.port;

import com.projeto.gerenciadordelivros.domain.model.Autor;

import java.util.List;

public interface AutorRepository {

    Autor salvar(Autor autor);

    List<Autor> listarTodos();
}
