package com.projeto.gerenciadordelivros.infrastructure.report;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RelatorioTemplateCompileTest {

    @Test
    void deveCompilarPreencherEExportarTemplate() throws Exception {
        try (InputStream template = new ClassPathResource("reports/relatorio_autor_livros.jrxml").getInputStream()) {
            var report = JasperCompileManager.compileReport(template);
            var data = new JRBeanCollectionDataSource(List.of(
                    new RelatorioAutorRow("Autor Teste", "Livro Teste", java.math.BigDecimal.TEN, "Assunto Teste")
            ));
            var print = JasperFillManager.fillReport(report, new HashMap<>(), data);
            byte[] pdf = JasperExportManager.exportReportToPdf(print);
            assertTrue(pdf.length > 0);
        }
    }
}
