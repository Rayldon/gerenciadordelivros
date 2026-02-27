package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.port.RelatorioAutoresPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GerarRelatorioAutoresUseCase {

    private final RelatorioAutoresPort relatorioAutoresPort;

    public byte[] executar() {
        return relatorioAutoresPort.gerarPdf();
    }
}
