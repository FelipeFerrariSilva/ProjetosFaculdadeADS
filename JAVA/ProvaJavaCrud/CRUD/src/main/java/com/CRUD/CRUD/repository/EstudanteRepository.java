package com.CRUD.CRUD.repository;

import com.CRUD.CRUD.entity.EstudanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudanteRepository extends JpaRepository<EstudanteEntity, Long> {
}