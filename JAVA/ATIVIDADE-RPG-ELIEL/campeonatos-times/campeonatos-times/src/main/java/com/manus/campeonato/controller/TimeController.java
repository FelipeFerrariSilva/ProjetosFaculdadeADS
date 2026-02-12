package com.manus.campeonato.controller;

import com.manus.campeonato.model.Time;
import com.manus.campeonato.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/times")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @PostMapping
    public ResponseEntity<Time> criarTime(@RequestBody Time time) {
        try {
            Time novoTime = timeService.salvar(time);
            return new ResponseEntity<>(novoTime, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Time>> listarTimes() {
        List<Time> times = timeService.buscarTodos();
        return new ResponseEntity<>(times, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Time> buscarTimePorId(@PathVariable Long id) {
        return timeService.buscarPorId(id)
                .map(time -> new ResponseEntity<>(time, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Time> atualizarTime(@PathVariable Long id, @RequestBody Time time) {
        if (!timeService.buscarPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        time.setId(id); // Garante que o ID correto seja usado para atualização
        try {
            Time timeAtualizado = timeService.salvar(time);
            return new ResponseEntity<>(timeAtualizado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTime(@PathVariable Long id) {
        if (!timeService.buscarPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        timeService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
