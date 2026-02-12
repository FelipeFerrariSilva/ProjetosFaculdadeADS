package com.CRUD.CRUD.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tabela_aluno")
public class EstudanteEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "nome")
        private String nome;

        @Column(name = "email")
        private String email;

        private Integer idade;

        public EstudanteEntity() {}

        public EstudanteEntity(String nome, String email, Integer idade) {
            this.nome = nome;
            this.email = email;
            this.idade = idade;
        }

        public Long getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getIdade() {
            return idade;
        }

        public void setIdade(Integer idade) {
            this.idade = idade;
        }
}