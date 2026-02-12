package com.teste.spring.teste.service;

import com.teste.spring.teste.exception.NotFoundException;
import com.teste.spring.teste.model.Sudoku;
import com.teste.spring.teste.repository.SudokuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SudokuService {

    private final SudokuRepository repository;

    public SudokuService(SudokuRepository repository) {
        this.repository = repository;
    }

    public List<Sudoku> listarTodos() {
        return repository.findAll();
    }


    public Sudoku salvar(Sudoku sudoku) {
        return repository.save(sudoku);
    }

    public Sudoku buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sudoku não encontrado"));
    }

    public void delete(Long id) {
        Sudoku sudoku = buscarPorId(id);
        repository.delete(sudoku);
    }

    public void deletar(Long id) {
    }
}