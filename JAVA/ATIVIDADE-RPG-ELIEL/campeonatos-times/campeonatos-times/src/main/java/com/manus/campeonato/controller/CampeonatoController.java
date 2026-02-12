package com.manus.campeonato.controller;

import com.manus.campeonato.model.Campeonato;
import com.manus.campeonato.service.CampeonatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/campeonatos")
public class CampeonatoController {

    @Autowired
    private CampeonatoService campeonatoService;

    @PostMapping
    public ResponseEntity<Campeonato> criarCampeonato(@RequestBody Campeonato campeonato) {
        try {
            Campeonato novoCampeonato = campeonatoService.salvar(campeonato);
            return new ResponseEntity<>(novoCampeonato, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Campeonato>> listarCampeonatos() {
        List<Campeonato> campeonatos = campeonatoService.buscarTodos();
        return new ResponseEntity<>(campeonatos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campeonato> buscarCampeonatoPorId(@PathVariable Long id) {
        return campeonatoService.buscarPorId(id)
                .map(campeonato -> new ResponseEntity<>(campeonato, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campeonato> atualizarCampeonato(@PathVariable Long id, @RequestBody Campeonato campeonato) {
        if (!campeonatoService.buscarPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        campeonato.setId(id); // Garante que o ID correto seja usado para atualização
        try {
            Campeonato campeonatoAtualizado = campeonatoService.salvar(campeonato);
            return new ResponseEntity<>(campeonatoAtualizado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCampeonato(@PathVariable Long id) {
        if (!campeonatoService.buscarPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        campeonatoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
