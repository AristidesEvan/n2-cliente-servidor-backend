package com.ebook.news.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebook.news.model.Cliente;
import com.ebook.news.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
