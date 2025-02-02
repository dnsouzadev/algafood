package com.dnsouzadev.algafood.api.model.mixin;

import com.dnsouzadev.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class CozinhaMixin {
    @JsonIgnore
    private List<Restaurante> restaurantes = new ArrayList<>();
}
