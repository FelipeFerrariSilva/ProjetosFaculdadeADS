package com.teste.spring.teste.dto;

public class SudokuDTO {

    private String tema;
    private int tamanho;
    private String dificuldade;



    public SudokuDTO(String tema, int tamanho, String dificuldade) {
        this.tema = tema;
        this.tamanho = tamanho;
        this.dificuldade = dificuldade;
    }


    public String getTema() {
        return tema;
    }

    public int getTamanho() {
        return tamanho;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }
}