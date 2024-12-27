package com.dnsouzadev.algafood.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Restaurante {

    @Id
    private Long id;

    private String nome;

    private BigDecimal taxaFrete;

}
