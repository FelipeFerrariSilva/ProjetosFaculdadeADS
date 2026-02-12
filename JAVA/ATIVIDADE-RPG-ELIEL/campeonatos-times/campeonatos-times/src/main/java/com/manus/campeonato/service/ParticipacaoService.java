package com.manus.campeonato.service;

import com.manus.campeonato.model.Participacao;
import com.manus.campeonato.repository.ParticipacaoRepository;
import com.manus.campeonato.repository.TimeRepository;
import com.manus.campeonato.repository.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipacaoService {

    @Autowired
    private ParticipacaoRepository participacaoRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    /**
     * Salva uma nova Participação ou atualiza uma existente.
     * Inclui validações para garantir a Confiabilidade e Corretude.
     * @param participacao O objeto Participacao a ser salvo.
     * @return A Participacao salva.
     */
    public Participacao salvar(Participacao participacao) {
        // 1. Validação de IDs de Time e Campeonato
        if (participacao.getTime() == null || participacao.getTime().getId() == null ||
            participacao.getCampeonato() == null || participacao.getCampeonato().getId() == null) {
            throw new IllegalArgumentException("Time e Campeonato devem ser informados.");
        }

        // 2. Busca e validação de existência (Confiabilidade)
        participacao.setTime(timeRepository.findById(participacao.getTime().getId())
                .orElseThrow(() -> new IllegalArgumentException("Time não encontrado.")));
        
        participacao.setCampeonato(campeonatoRepository.findById(participacao.getCampeonato().getId())
                .orElseThrow(() -> new IllegalArgumentException("Campeonato não encontrado.")));

        // 3. Validação de dados de pontuação e colocação (Confiabilidade)
        if (participacao.getPontuacao() < 0) {
            throw new IllegalArgumentException("A pontuação não pode ser negativa.");
        }
        if (participacao.getColocacao() < 1) {
            throw new IllegalArgumentException("A colocação deve ser um número positivo maior ou igual a 1.");
        }

        return participacaoRepository.save(participacao);
    }

    /**
     * Busca todas as Participações em um Campeonato, ordenadas pela colocação.
     * @param campeonatoId ID do Campeonato.
     * @return Lista de Participacoes.
     */
    public List<Participacao> buscarPorCampeonato(Long campeonatoId) {
        if (!campeonatoRepository.existsById(campeonatoId)) {
            throw new IllegalArgumentException("Campeonato não encontrado.");
        }
        return participacaoRepository.findByCampeonatoIdOrderByColocacaoAsc(campeonatoId);
    }

    /**
     * Busca uma Participação pelo ID.
     * @param id O ID da Participação.
     * @return Um Optional contendo a Participação, se encontrada.
     */
    public Optional<Participacao> buscarPorId(Long id) {
        return participacaoRepository.findById(id);
    }

    /**
     * Deleta uma Participação pelo ID.
     * @param id O ID da Participação a ser deletada.
     */
    public void deletar(Long id) {
        participacaoRepository.deleteById(id);
    }
}
