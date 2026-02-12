package com.livros.biblioteca.repositories;

import com.livros.biblioteca.entities.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository <LivroEntity, Long> {
}
