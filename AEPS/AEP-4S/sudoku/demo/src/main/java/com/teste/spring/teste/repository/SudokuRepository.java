package com.teste.spring.teste.repository;

import com.teste.spring.teste.model.Sudoku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SudokuRepository extends JpaRepository<Sudoku, Long> {

}