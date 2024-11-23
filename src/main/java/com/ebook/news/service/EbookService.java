package com.ebook.news.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebook.news.model.Ebook;
import com.ebook.news.repository.EbookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EbookService {
    private final EbookRepository ebookRepository;

    public Ebook salvarEbook(Ebook ebook) {
        return ebookRepository.save(ebook);
    }

    public List<Ebook> listarEbooks() {
        return ebookRepository.findAll();
    }

    public Ebook buscarPorId(Long id) {
        return ebookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ebook não encontrado."));
    }

    public void deletarEbook(Long id) {
        ebookRepository.deleteById(id);
    }

    public List<Ebook> buscarPorCategoria(String categoria) {
        return ebookRepository.findByCategoria(categoria);
    }
}
