package com.manus.campeonato.service;

import com.manus.campeonato.model.Campeonato;
import com.manus.campeonato.repository.CampeonatoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampeonatoServiceTest {

    @Mock
    private CampeonatoRepository campeonatoRepository;

    @InjectMocks
    private CampeonatoService campeonatoService;

    private Campeonato campeonatoValido;

    @BeforeEach
    void setUp() {
        campeonatoValido = new Campeonato(1L, "Brasileirão", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31));
    }

    @Test
    void salvar_DeveSalvarCampeonatoValido() {
        when(campeonatoRepository.save(any(Campeonato.class))).thenReturn(campeonatoValido);

        Campeonato salvo = campeonatoService.salvar(campeonatoValido);

        assertNotNull(salvo);
        assertEquals("Brasileirão", salvo.getNome());
        verify(campeonatoRepository, times(1)).save(campeonatoValido);
    }

    @Test
    void salvar_DeveLancarExcecaoQuandoDataFinalAnteriorADataInicial() {
        Campeonato campeonatoInvalido = new Campeonato(2L, "Invalido", LocalDate.of(2025, 12, 31), LocalDate.of(2025, 1, 1));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            campeonatoService.salvar(campeonatoInvalido);
        });

        assertEquals("A data final do Campeonato não pode ser anterior à data inicial.", exception.getMessage());
        verify(campeonatoRepository, never()).save(any(Campeonato.class));
    }

    @Test
    void salvar_DeveLancarExcecaoQuandoNomeVazio() {
        Campeonato campeonatoInvalido = new Campeonato(2L, "", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            campeonatoService.salvar(campeonatoInvalido);
        });

        assertEquals("O nome do Campeonato não pode ser vazio.", exception.getMessage());
        verify(campeonatoRepository, never()).save(any(Campeonato.class));
    }

    @Test
    void buscarPorId_DeveRetornarCampeonatoQuandoEncontrado() {
        when(campeonatoRepository.findById(1L)).thenReturn(Optional.of(campeonatoValido));

        Optional<Campeonato> encontrado = campeonatoService.buscarPorId(1L);

        assertTrue(encontrado.isPresent());
        assertEquals("Brasileirão", encontrado.get().getNome());
    }
}
