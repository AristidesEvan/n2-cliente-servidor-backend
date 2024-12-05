package com.ebook.news.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebook.news.model.Cliente;
import com.ebook.news.model.LoginRequest;
import com.ebook.news.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ClienteRepository clienteRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Buscar o cliente pelo email
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(loginRequest.getEmail());

        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        Cliente cliente = clienteOpt.get();

        // Comparar a senha diretamente
        if (!cliente.getSenha().equals(loginRequest.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta.");
        }

        // Retornar o cliente se a autenticação for bem-sucedida
        return ResponseEntity.ok(cliente);
    }
}
