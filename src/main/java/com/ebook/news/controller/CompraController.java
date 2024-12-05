package com.ebook.news.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ebook.news.model.Compra;
import com.ebook.news.service.CompraService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {
    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<Compra> registrarCompra(@RequestBody Compra compra) {
        return ResponseEntity.ok(compraService.registrarCompra(compra));
    }

    @GetMapping
    public ResponseEntity<List<Compra>> listarCompras() {
        return ResponseEntity.ok(compraService.listarCompras());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> buscarCompra(@PathVariable Long id) {
        return ResponseEntity.ok(compraService.buscarPorId(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Compra>> listarComprasPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(compraService.listarComprasPorCliente(clienteId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCompra(@PathVariable Long id) {
        compraService.deletarCompra(id);
        return ResponseEntity.noContent().build();
    }
}
