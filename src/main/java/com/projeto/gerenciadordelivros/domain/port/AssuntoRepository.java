package com.projeto.gerenciadordelivros.domain.port;

import com.projeto.gerenciadordelivros.domain.model.Assunto;

import java.util.List;
import java.util.Optional;

public interface AssuntoRepository {

    Assunto salvar(Assunto assunto);

    List<Assunto> listarTodos();

    Optional<Assunto> buscarPorId(Long id);

    Optional<Assunto> atualizar(Long id, Assunto assunto);

    boolean existePorId(Long id);

    void deletarPorId(Long id);

    boolean possuiLivrosVinculados(Long id);
}
