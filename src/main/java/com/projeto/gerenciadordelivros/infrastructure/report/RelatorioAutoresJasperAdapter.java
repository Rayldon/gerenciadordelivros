package com.projeto.gerenciadordelivros.infrastructure.report;

import com.projeto.gerenciadordelivros.domain.port.RelatorioAutoresPort;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Component
public class RelatorioAutoresJasperAdapter implements RelatorioAutoresPort {

    private final RelatorioAutoresJpaRepository relatorioAutoresJpaRepository;

    public RelatorioAutoresJasperAdapter(RelatorioAutoresJpaRepository relatorioAutoresJpaRepository) {
        this.relatorioAutoresJpaRepository = relatorioAutoresJpaRepository;
    }

    @Override
    public byte[] gerarPdf() {
        List<RelatorioAutorRow> linhas = buscarDados();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(linhas);

        try (InputStream template = new ClassPathResource("reports/relatorio_autor_livros.jrxml").getInputStream()) {
            var jasperReport = JasperCompileManager.compileReport(template);
            var jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (IOException | JRException ex) {
            throw new IllegalStateException("Falha ao gerar relatorio de autores.", ex);
        }
    }

    private List<RelatorioAutorRow> buscarDados() {
        return relatorioAutoresJpaRepository.buscarDadosRelatorio()
                .stream()
                .map(linha -> new RelatorioAutorRow(
                        linha.getAutorNome(),
                        linha.getLivroTitulo(),
                        linha.getLivroValor(),
                        linha.getAssuntoDescricao()
                ))
                .toList();
    }
}
