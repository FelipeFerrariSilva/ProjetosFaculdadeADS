package com.manus.campeonato.service;

import com.manus.campeonato.model.Campeonato;
import com.manus.campeonato.model.Participacao;
import com.manus.campeonato.model.Time;
import com.manus.campeonato.repository.CampeonatoRepository;
import com.manus.campeonato.repository.ParticipacaoRepository;
import com.manus.campeonato.repository.TimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParticipacaoServiceTest {

    @Mock
    private ParticipacaoRepository participacaoRepository;

    @Mock
    private TimeRepository timeRepository;

    @Mock
    private CampeonatoRepository campeonatoRepository;

    @InjectMocks
    private ParticipacaoService participacaoService;

    private Time time;
    private Campeonato campeonato;
    private Participacao participacao;

    @BeforeEach
    void setUp() {
        time = new Time(1L, "Time Teste", "Cidade Teste");
        campeonato = new Campeonato(1L, "Camp Teste", LocalDate.now(), LocalDate.now().plusDays(1));
        participacao = new Participacao(null, time, campeonato, 10, 1);
    }

    @Test
    void salvar_DeveSalvarParticipacaoValida() {
        when(timeRepository.findById(1L)).thenReturn(Optional.of(time));
        when(campeonatoRepository.findById(1L)).thenReturn(Optional.of(campeonato));
        when(participacaoRepository.save(any(Participacao.class))).thenReturn(participacao);

        Participacao salvo = participacaoService.salvar(participacao);

        assertNotNull(salvo);
        assertEquals(10, salvo.getPontuacao());
        verify(participacaoRepository, times(1)).save(participacao);
    }

    @Test
    void salvar_DeveLancarExcecaoQuandoTimeNaoEncontrado() {
        when(timeRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            participacaoService.salvar(participacao);
        });

        assertEquals("Time não encontrado.", exception.getMessage());
        verify(participacaoRepository, never()).save(any(Participacao.class));
    }

    @Test
    void salvar_DeveLancarExcecaoQuandoPontuacaoNegativa() {
        participacao.setPontuacao(-5);
        when(timeRepository.findById(1L)).thenReturn(Optional.of(time));
        when(campeonatoRepository.findById(1L)).thenReturn(Optional.of(campeonato));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            participacaoService.salvar(participacao);
        });

        assertEquals("A pontuação não pode ser negativa.", exception.getMessage());
    }

    @Test
    void buscarPorCampeonato_DeveRetornarListaOrdenada() {
        Participacao p1 = new Participacao(1L, time, campeonato, 10, 1);
        Participacao p2 = new Participacao(2L, time, campeonato, 5, 2);
        List<Participacao> lista = Arrays.asList(p1, p2);

        when(campeonatoRepository.existsById(1L)).thenReturn(true);
        when(participacaoRepository.findByCampeonatoIdOrderByColocacaoAsc(1L)).thenReturn(lista);

        List<Participacao> encontradas = participacaoService.buscarPorCampeonato(1L);

        assertNotNull(encontradas);
        assertEquals(2, encontradas.size());
        assertEquals(1, encontradas.get(0).getColocacao());
        assertEquals(2, encontradas.get(1).getColocacao());
    }
}
