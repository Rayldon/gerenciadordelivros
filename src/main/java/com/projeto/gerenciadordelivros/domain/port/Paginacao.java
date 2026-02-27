package com.projeto.gerenciadordelivros.domain.port;

import java.util.List;

public record Paginacao<T>(
        List<T> itens,
        int pagina,
        int tamanho,
        long totalItens,
        int totalPaginas,
        boolean primeira,
        boolean ultima
) {
}
