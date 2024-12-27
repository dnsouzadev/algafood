package com.dnsouzadev.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_cozinhas")
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_cozinha")
    private String nome;

    public Cozinha() {
    }

    public Cozinha(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
