package com.dnsouzadev.algafood.api.model;

import com.dnsouzadev.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

public class CozinhaModel {

    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @JsonView(RestauranteView.Resumo.class)
    private String nome;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
