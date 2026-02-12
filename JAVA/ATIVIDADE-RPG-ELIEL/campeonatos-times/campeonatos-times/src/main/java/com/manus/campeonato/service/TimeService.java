package com.manus.campeonato.service;

import com.manus.campeonato.model.Time;
import com.manus.campeonato.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    /**
     * Salva um novo Time ou atualiza um existente.
     * @param time O objeto Time a ser salvo.
     * @return O Time salvo.
     */
    public Time salvar(Time time) {
        // Validação básica para garantir a Confiabilidade e Corretude
        if (time.getNome() == null || time.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do Time não pode ser vazio.");
        }
        return timeRepository.save(time);
    }

    /**
     * Busca todos os Times.
     * @return Uma lista de todos os Times.
     */
    public List<Time> buscarTodos() {
        return timeRepository.findAll();
    }

    /**
     * Busca um Time pelo ID.
     * @param id O ID do Time.
     * @return Um Optional contendo o Time, se encontrado.
     */
    public Optional<Time> buscarPorId(Long id) {
        return timeRepository.findById(id);
    }

    /**
     * Deleta um Time pelo ID.
     * @param id O ID do Time a ser deletado.
     */
    public void deletar(Long id) {
        timeRepository.deleteById(id);
    }
}
