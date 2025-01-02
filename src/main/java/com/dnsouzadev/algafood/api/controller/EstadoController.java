package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.domain.model.Estado;
import com.dnsouzadev.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(Long estadoId) {
        return estadoRepository.buscar(estadoId);
    }

    @PostMapping
    public Estado adicionar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    @DeleteMapping("/{estadoId}")
    public void remover(@PathVariable Long estadoId) {
        estadoRepository.remover(estadoRepository.buscar(estadoId));
    }

}
