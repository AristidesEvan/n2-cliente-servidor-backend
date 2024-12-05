package com.ebook.news.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebook.news.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/clientes")
    public ResponseEntity<byte[]> generateClientReport() {
        try {
            byte[] report = reportService.generateClientReport();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clientes.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(report);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/compras/{clientId}")
    public ResponseEntity<byte[]> generatePurchaseReport(@PathVariable Long clientId) {
        try {
            byte[] report = reportService.generatePurchaseReport(clientId);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=compras_cliente_" + clientId + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(report);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
