package com.dnsouzadev.algafood.api.model.input;

import jakarta.validation.constraints.NotNull;

public class RestauranteIdInput {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
