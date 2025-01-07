package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.dnsouzadev.algafood.domain.model.Cidade;
import com.dnsouzadev.algafood.domain.service.CadastroCidadeService;
import com.dnsouzadev.algafood.domain.service.ListarCidadeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private ListarCidadeService listarCidadeService;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> listar() {
        return ResponseEntity.ok(listarCidadeService.listar());
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
        return listarCidadeService.buscar(cidadeId) != null ? ResponseEntity.ok(listarCidadeService.buscar(cidadeId)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
        try {
            return ResponseEntity.ok(cadastroCidadeService.salvar(cidade));
        } catch (EntidadeNaoEncontradaException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("Erro ao adicionar cidade: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body("Erro ao adicionar cidade: " + e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        Cidade cidadeAtual = listarCidadeService.buscar(cidadeId);
        if (cidadeAtual != null) {
            return ResponseEntity.ok(cadastroCidadeService.atualizar(cidadeId, cidade));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Void> remover(@PathVariable Long cidadeId) {
        cadastroCidadeService.excluir(listarCidadeService.buscar(cidadeId).getId());
        return ResponseEntity.noContent().build();
    }

}
