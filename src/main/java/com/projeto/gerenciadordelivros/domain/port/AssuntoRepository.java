package com.projeto.gerenciadordelivros.domain.port;

import com.projeto.gerenciadordelivros.domain.model.Assunto;

import java.util.List;

public interface AssuntoRepository {

    Assunto salvar(Assunto assunto);

    List<Assunto> listarTodos();
}
