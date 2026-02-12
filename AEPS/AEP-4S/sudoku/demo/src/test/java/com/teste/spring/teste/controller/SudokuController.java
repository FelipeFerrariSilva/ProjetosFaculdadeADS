package com.teste.spring.teste.controller;

import com.teste.spring.teste.model.Sudoku;
import com.teste.spring.teste.service.SudokuService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sudoku")
public class SudokuController {

    private final SudokuService service;

    public SudokuController(SudokuService service) {
        this.service = service;
    }

    @GetMapping
    public List<Sudoku> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Sudoku criar(@RequestBody Sudoku sudoku) {
        return service.salvar(sudoku);
    }

    @GetMapping("/{id}")
    public Sudoku buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
