package com.CRUD.CRUD.service;

import com.CRUD.CRUD.entity.EstudanteEntity;
import com.CRUD.CRUD.repository.EstudanteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstudanteService {

    private final EstudanteRepository repository;

    public EstudanteService(EstudanteRepository repository) {
        this.repository = repository;
    }

    public EstudanteEntity criarEstudante(EstudanteEntity estudante) {
        return repository.save(estudante);
    }

    public List<EstudanteEntity> listarTodos() {
        return repository.findAll();
    }

    public Optional<EstudanteEntity> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}