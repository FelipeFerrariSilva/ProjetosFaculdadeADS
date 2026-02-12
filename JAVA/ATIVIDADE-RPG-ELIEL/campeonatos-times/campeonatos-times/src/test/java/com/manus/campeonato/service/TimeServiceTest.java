package com.manus.campeonato.service;

import com.manus.campeonato.model.Time;
import com.manus.campeonato.repository.TimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TimeServiceTest {

    @Mock
    private TimeRepository timeRepository;

    @InjectMocks
    private TimeService timeService;

    private Time timeA;
    private Time timeB;

    @BeforeEach
    void setUp() {
        timeA = new Time(1L, "Time A", "Cidade A");
        timeB = new Time(2L, "Time B", "Cidade B");
    }

    @Test
    void salvar_DeveSalvarTimeComSucesso() {
        when(timeRepository.save(any(Time.class))).thenReturn(timeA);

        Time salvo = timeService.salvar(timeA);

        assertNotNull(salvo);
        assertEquals("Time A", salvo.getNome());
        verify(timeRepository, times(1)).save(timeA);
    }

    @Test
    void salvar_DeveLancarExcecaoQuandoNomeVazio() {
        Time timeInvalido = new Time(null, "", "Cidade C");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            timeService.salvar(timeInvalido);
        });

        assertEquals("O nome do Time não pode ser vazio.", exception.getMessage());
        verify(timeRepository, never()).save(any(Time.class));
    }

    @Test
    void buscarTodos_DeveRetornarListaDeTimes() {
        List<Time> times = Arrays.asList(timeA, timeB);
        when(timeRepository.findAll()).thenReturn(times);

        List<Time> encontrados = timeService.buscarTodos();

        assertNotNull(encontrados);
        assertEquals(2, encontrados.size());
        verify(timeRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_DeveRetornarTimeQuandoEncontrado() {
        when(timeRepository.findById(1L)).thenReturn(Optional.of(timeA));

        Optional<Time> encontrado = timeService.buscarPorId(1L);

        assertTrue(encontrado.isPresent());
        assertEquals("Time A", encontrado.get().getNome());
        verify(timeRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPorId_DeveRetornarVazioQuandoNaoEncontrado() {
        when(timeRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Time> encontrado = timeService.buscarPorId(99L);

        assertFalse(encontrado.isPresent());
        verify(timeRepository, times(1)).findById(99L);
    }

    @Test
    void deletar_DeveChamarDeleteById() {
        timeService.deletar(1L);
        verify(timeRepository, times(1)).deleteById(1L);
    }
}
