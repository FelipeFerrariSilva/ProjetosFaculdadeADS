package com.manus.campeonato.service;

import com.manus.campeonato.model.Campeonato;
import com.manus.campeonato.repository.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CampeonatoService {

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    /**
     * Salva um novo Campeonato ou atualiza um existente.
     * Inclui validação para garantir a Confiabilidade (dataFinal > dataInicial).
     * @param campeonato O objeto Campeonato a ser salvo.
     * @return O Campeonato salvo.
     */
    public Campeonato salvar(Campeonato campeonato) {
        if (campeonato.getNome() == null || campeonato.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do Campeonato não pode ser vazio.");
        }
        if (campeonato.getDataInicial() == null || campeonato.getDataFinal() == null) {
            throw new IllegalArgumentException("As datas inicial e final do Campeonato são obrigatórias.");
        }
        if (campeonato.getDataFinal().isBefore(campeonato.getDataInicial())) {
            throw new IllegalArgumentException("A data final do Campeonato não pode ser anterior à data inicial.");
        }
        return campeonatoRepository.save(campeonato);
    }

    /**
     * Busca todos os Campeonatos.
     * @return Uma lista de todos os Campeonatos.
     */
    public List<Campeonato> buscarTodos() {
        return campeonatoRepository.findAll();
    }

    /**
     * Busca um Campeonato pelo ID.
     * @param id O ID do Campeonato.
     * @return Um Optional contendo o Campeonato, se encontrado.
     */
    public Optional<Campeonato> buscarPorId(Long id) {
        return campeonatoRepository.findById(id);
    }

    /**
     * Deleta um Campeonato pelo ID.
     * @param id O ID do Campeonato a ser deletado.
     */
    public void deletar(Long id) {
        campeonatoRepository.deleteById(id);
    }
}
