package com.projeto.gerenciadordelivros.infrastructure.report;

import com.projeto.gerenciadordelivros.infrastructure.persistence.entity.AutorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RelatorioAutoresJpaRepository extends Repository<AutorEntity, Long> {

    @Query(value = """
            SELECT autor_nome AS autorNome,
                   livro_titulo AS livroTitulo,
                   livro_valor AS livroValor,
                   assunto_descricao AS assuntoDescricao
            FROM vw_relatorio_autor
            ORDER BY autor_nome, livro_titulo, assunto_descricao
            """, nativeQuery = true)
    List<RelatorioAutorProjection> buscarDadosRelatorio();
}
