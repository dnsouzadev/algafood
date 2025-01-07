package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.domain.model.Restaurante;
import com.dnsouzadev.algafood.domain.service.ListarRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private ListarRestauranteService listarRestauranteService;

    @GetMapping
    public List<Restaurante> listar() {
        return listarRestauranteService.listar();
    }

    @GetMapping("/{restauranteId}")
    public Restaurante buscar(Long restauranteId) {
        return listarRestauranteService.buscar(restauranteId);
    }



}
