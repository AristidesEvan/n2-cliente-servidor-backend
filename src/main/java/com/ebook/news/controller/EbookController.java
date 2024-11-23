package com.ebook.news.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebook.news.model.Ebook;
import com.ebook.news.service.EbookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ebooks")
@RequiredArgsConstructor
public class EbookController {
    private final EbookService ebookService;

    @PostMapping
    public ResponseEntity<Ebook> criarEbook(@RequestBody Ebook ebook) {
        return ResponseEntity.ok(ebookService.salvarEbook(ebook));
    }

    @GetMapping
    public ResponseEntity<List<Ebook>> listarEbooks() {
        return ResponseEntity.ok(ebookService.listarEbooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ebook> buscarEbook(@PathVariable Long id) {
        return ResponseEntity.ok(ebookService.buscarPorId(id));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Ebook>> buscarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(ebookService.buscarPorCategoria(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEbook(@PathVariable Long id) {
        ebookService.deletarEbook(id);
        return ResponseEntity.noContent().build();
    }
}
