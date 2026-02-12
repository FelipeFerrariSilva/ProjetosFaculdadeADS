package com.livros.biblioteca.services;

import com.livros.biblioteca.entities.LivroEntity;
import com.livros.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public void criarLivro(LivroEntity livroEntity) {
        livroRepository.save(livroEntity);
    }

    public void atualizarLivro(Long id, LivroEntity livroEntity) {
        var findlivrosEntity = livroRepository.findById(id).orElse(null);
        livroRepository.save(findlivrosEntity);
    }

    public List<LivroEntity> findLivrosEntity() {
        return livroRepository.findAll();
    }

    public void deletarLivro (Long id) {
        var livroEntity = livroRepository.findById(id);
        if (livroEntity.isPresent()){
            livroRepository.deleteById(id);
        }
    }
}