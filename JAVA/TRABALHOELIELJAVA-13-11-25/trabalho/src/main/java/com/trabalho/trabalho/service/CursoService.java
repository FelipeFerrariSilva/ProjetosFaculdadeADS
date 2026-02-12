package com.trabalho.trabalho.service;

import com.trabalho.trabalho.model.Curso;
import com.trabalho.trabalho.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso criar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso atualizar(Long id, Curso cursoAtualizado) {
        return cursoRepository.findById(id)
                .map(curso -> {
                    curso.setNome(cursoAtualizado.getNome());
                    curso.setCargaHoraria(cursoAtualizado.getCargaHoraria());
                    return cursoRepository.save(curso);
                })
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com id: " + id));
    }

    public void excluir(Long id) {
        cursoRepository.deleteById(id);
    }
}