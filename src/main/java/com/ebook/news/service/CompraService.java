package com.ebook.news.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebook.news.model.Compra;
import com.ebook.news.repository.CompraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraService {
    private final CompraRepository compraRepository;

    public Compra registrarCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    public Compra buscarPorId(Long id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada."));
    }

    public List<Compra> listarComprasPorCliente(Long clienteId) {
        return compraRepository.findByClienteId(clienteId);
    }

    public void deletarCompra(Long id) {
        compraRepository.deleteById(id);
    }
}
