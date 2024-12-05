package com.ebook.news.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final DataSource dataSource;

    public byte[] generateClientReport() {
        try {
            InputStream reportStream = getClass().getResourceAsStream("/report/clientes.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace(); // Log do erro no console
            throw new RuntimeException("Erro ao gerar o relatório de clientes", e);
        }
    }
    

    public byte[] generatePurchaseReport(Long clientId) throws Exception {
        // Caminho do relatório
        InputStream reportStream = getClass().getResourceAsStream("/reports/compras_por_cliente.jrxml");

        // Compilar o relatório
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Parâmetros do relatório
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CLIENT_ID", clientId);

        // Preencher o relatório com dados do banco
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

        // Exportar para PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
