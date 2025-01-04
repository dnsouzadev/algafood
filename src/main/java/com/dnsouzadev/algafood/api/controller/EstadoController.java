package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.domain.model.Estado;
import com.dnsouzadev.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        return ResponseEntity.ok(estadoRepository.listar());
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(Long estadoId) {
        return estadoRepository.buscar(estadoId) != null ? ResponseEntity.ok(estadoRepository.buscar(estadoId)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Estado> adicionar(Estado estado) {
        return ResponseEntity.ok(estadoRepository.salvar(estado));
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Void> remover(@PathVariable Long estadoId) {
        estadoRepository.remover(estadoRepository.buscar(estadoId));
        return ResponseEntity.noContent().build();
    }

}
