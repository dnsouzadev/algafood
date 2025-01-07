package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.dnsouzadev.algafood.domain.model.Restaurante;
import com.dnsouzadev.algafood.domain.service.CadastroRestauranteService;
import com.dnsouzadev.algafood.domain.service.ListarRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private ListarRestauranteService listarRestauranteService;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {
        return ResponseEntity.ok(listarRestauranteService.listar());
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
        if (listarRestauranteService.buscar(restauranteId) != null) {
            return ResponseEntity.ok(listarRestauranteService.buscar(restauranteId));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = cadastroRestauranteService.salvar(restaurante);
            System.out.println("Restaurante adicionado: " + restaurante);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao adicionar restaurante: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar restaurante: " + e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteAtual = cadastroRestauranteService.atualizar(restauranteId, restaurante);
            return ResponseEntity.ok().body(restauranteAtual);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe um cadastro de restaurante com código " + restauranteId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar restaurante: " + e.getMessage());
        }
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<?> remover(@PathVariable Long restauranteId) {
        try {
            cadastroRestauranteService.excluir(restauranteId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe um cadastro de restaurante com código " + restauranteId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao remover restaurante: " + e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {
        Restaurante restauranteAtual = listarRestauranteService.buscar(restauranteId);

        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual);

        return atualizar(restauranteId, restauranteAtual);
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            assert field != null;
            field.setAccessible(true);

            Object valorConvertido = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, valorConvertido);
        });
    }
}
