package com.livros.biblioteca.controllers;

import com.livros.biblioteca.entities.LivroEntity;
import com.livros.biblioteca.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")

public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public void criarLivro(@RequestBody LivroEntity livroEntity) {
        livroService.criarLivro(livroEntity);
    }

    @GetMapping
    public List<LivroEntity> getLivroService (){
        return livroService.findLivrosEntity();
    }

    @PostMapping ("/{id}")
    public void atualizarLivro(@PathVariable Long id, @RequestBody LivroEntity livroEntity){
        livroService.atualizarLivro(id, livroEntity);
    }

    @DeleteMapping("/{id}")
    public void deletarLivro(@PathVariable Long id){
        livroService.deletarLivro(id);
    }

}