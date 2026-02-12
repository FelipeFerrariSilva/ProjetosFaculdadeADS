package com.trabalho.trabalho.service;

import com.trabalho.trabalho.model.Aluno;
import com.trabalho.trabalho.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno criar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public Aluno atualizar(Long id, Aluno alunoAtualizado) {
        return alunoRepository.findById(id)
                .map(aluno -> {
                    aluno.setNome(alunoAtualizado.getNome());
                    aluno.setMatricula(alunoAtualizado.getMatricula());
                    aluno.setStatus(alunoAtualizado.getStatus());
                    aluno.setCurso(alunoAtualizado.getCurso());
                    return alunoRepository.save(aluno);
                })
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com id: " + id));
    }

    public void excluir(Long id) {
        alunoRepository.deleteById(id);
    }
}