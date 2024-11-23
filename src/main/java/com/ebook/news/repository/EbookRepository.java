package com.ebook.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebook.news.model.Ebook;

@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long> {
    List<Ebook> findByCategoria(String categoria);
}
