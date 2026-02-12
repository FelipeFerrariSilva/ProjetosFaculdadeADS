package com.teste.spring.teste.mapa;

import java.util.Arrays;


public class SudokuMapa {

    private final int tamanho;
    private final String[][] tabuleiro;


    public SudokuMapa(int tamanho) {
        this.tamanho = tamanho;

        this.tabuleiro = new String[tamanho][tamanho];
    }

    public void preencherPosicao(int linha, int coluna, String valor) {

        if (linha >= 0 && linha < tamanho && coluna >= 0 && coluna < tamanho) {
            tabuleiro[linha][coluna] = valor;
        } else {
            System.err.println("Posição (" + linha + ", " + coluna + ") inválida.");
        }
    }

    public void exibir() {
        for (String[] linha : tabuleiro) {
            System.out.println(Arrays.toString(linha));
        }
    }


    public int getTamanho() {
        return tamanho;
    }

    public String[][] getTabuleiro() {
        return tabuleiro;
    }
}