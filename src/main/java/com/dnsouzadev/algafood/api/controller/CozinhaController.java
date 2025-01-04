package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.domain.model.Cozinha;
import com.dnsouzadev.algafood.domain.repository.CozinhaRepository;
import com.dnsouzadev.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
        return ResponseEntity.ok(cozinhaRepository.listar());
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        if (cozinhaRepository.buscar(cozinhaId) != null) {
            return ResponseEntity.ok(cozinhaRepository.buscar(cozinhaId));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        return ResponseEntity.ok(cadastroCozinha.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
        if (cozinhaAtual != null) {
            cozinhaAtual.setNome(cozinha.getNome());
            return ResponseEntity.ok(cozinhaRepository.salvar(cozinhaAtual));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> remover(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        if (cozinha != null) {
            cozinhaRepository.remover(cozinha);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
