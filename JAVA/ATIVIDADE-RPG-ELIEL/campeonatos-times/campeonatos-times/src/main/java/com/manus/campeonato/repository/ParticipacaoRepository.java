package com.manus.campeonato.repository;

import com.manus.campeonato.model.Participacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParticipacaoRepository extends JpaRepository<Participacao, Long> {
    
    /**
     * Busca todas as participações de um time em todos os campeonatos.
     * @param timeId ID do Time
     * @return Lista de Participacoes
     */
    List<Participacao> findByTimeId(Long timeId);

    /**
     * Busca todas as participações em um campeonato específico, ordenadas pela colocação.
     * @param campeonatoId ID do Campeonato
     * @return Lista de Participacoes
     */
    List<Participacao> findByCampeonatoIdOrderByColocacaoAsc(Long campeonatoId);
}
