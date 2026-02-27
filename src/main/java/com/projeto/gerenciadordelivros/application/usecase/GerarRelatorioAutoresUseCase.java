package com.projeto.gerenciadordelivros.application.usecase;

import com.projeto.gerenciadordelivros.domain.port.RelatorioAutoresPort;
import org.springframework.stereotype.Service;

@Service
public class GerarRelatorioAutoresUseCase {

    private final RelatorioAutoresPort relatorioAutoresPort;

    public GerarRelatorioAutoresUseCase(RelatorioAutoresPort relatorioAutoresPort) {
        this.relatorioAutoresPort = relatorioAutoresPort;
    }

    public byte[] executar() {
        return relatorioAutoresPort.gerarPdf();
    }
}
