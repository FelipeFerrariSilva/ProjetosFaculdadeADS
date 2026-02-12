package com.manus.campeonato.controller;

import com.manus.campeonato.model.Participacao;
import com.manus.campeonato.service.ParticipacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/participacoes")
public class ParticipacaoController {

    @Autowired
    private ParticipacaoService participacaoService;

    @PostMapping
    public ResponseEntity<Participacao> criarParticipacao(@RequestBody Participacao participacao) {
        try {
            Participacao novaParticipacao = participacaoService.salvar(participacao);
            return new ResponseEntity<>(novaParticipacao, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Captura exceções de validação (Time/Campeonato não encontrado, dados inválidos)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/campeonato/{campeonatoId}")
    public ResponseEntity<List<Participacao>> listarParticipacoesPorCampeonato(@PathVariable Long campeonatoId) {
        try {
            List<Participacao> participacoes = participacaoService.buscarPorCampeonato(campeonatoId);
            return new ResponseEntity<>(participacoes, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participacao> buscarParticipacaoPorId(@PathVariable Long id) {
        return participacaoService.buscarPorId(id)
                .map(participacao -> new ResponseEntity<>(participacao, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participacao> atualizarParticipacao(@PathVariable Long id, @RequestBody Participacao participacao) {
        if (!participacaoService.buscarPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        participacao.setId(id); // Garante que o ID correto seja usado para atualização
        try {
            Participacao participacaoAtualizada = participacaoService.salvar(participacao);
            return new ResponseEntity<>(participacaoAtualizada, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarParticipacao(@PathVariable Long id) {
        if (!participacaoService.buscarPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        participacaoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
