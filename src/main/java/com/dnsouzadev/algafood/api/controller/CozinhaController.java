package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.domain.exception.EntidadeEmUsoException;
import com.dnsouzadev.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.dnsouzadev.algafood.domain.model.Cozinha;
import com.dnsouzadev.algafood.domain.repository.CozinhaRepository;
import com.dnsouzadev.algafood.domain.service.CadastroCozinhaService;
import com.dnsouzadev.algafood.domain.service.ListarCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private ListarCozinhaService listarCozinhaService;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
        return ResponseEntity.ok(listarCozinhaService.listar());
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        if (listarCozinhaService.buscar(cozinhaId) != null) {
            return ResponseEntity.ok(listarCozinhaService.buscar(cozinhaId));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        return ResponseEntity.ok(cadastroCozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cadastroCozinhaService.atualizar(cozinhaId, cozinha);
        return ResponseEntity.ok(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.excluir(cozinhaId);
    }
}
