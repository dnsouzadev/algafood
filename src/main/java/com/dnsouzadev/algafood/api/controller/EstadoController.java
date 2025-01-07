package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.domain.model.Estado;
import com.dnsouzadev.algafood.domain.service.CadastroEstadoService;
import com.dnsouzadev.algafood.domain.service.ListarEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private ListarEstadoService listarEstadoService;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        return ResponseEntity.ok(listarEstadoService.listar());
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(Long estadoId) {
        return listarEstadoService.buscar(estadoId) != null ? ResponseEntity.ok(listarEstadoService.buscar(estadoId)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Estado> adicionar(Estado estado) {
        return ResponseEntity.ok(cadastroEstadoService.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        Estado estadoAtual = listarEstadoService.buscar(estadoId);
        if (estadoAtual != null) {
            return ResponseEntity.ok(cadastroEstadoService.atualizar(estado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Void> remover(@PathVariable Long estadoId) {
        cadastroEstadoService.excluir(listarEstadoService.buscar(estadoId).getId());
        return ResponseEntity.noContent().build();
    }

}
