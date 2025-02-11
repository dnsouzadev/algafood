package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.assembler.ProdutoInputDisassemble;
import com.dnsouzadev.algafood.api.assembler.ProdutoModelAssembler;
import com.dnsouzadev.algafood.api.model.ProdutoModel;
import com.dnsouzadev.algafood.api.model.input.ProdutoInput;
import com.dnsouzadev.algafood.domain.model.Produto;
import com.dnsouzadev.algafood.domain.service.ProdutoService;
import com.dnsouzadev.algafood.domain.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassemble produtoInputDisassemble;

    @GetMapping
    public List<ProdutoModel> listar(@PathVariable Long restauranteId,
                                     @RequestParam(required = false) boolean incluirInativos) {
        return produtoModelAssembler.toCollectionModel(produtoService.listar(restauranteId, incluirInativos));
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        return produtoModelAssembler.toModel(produtoService.buscarOuFalhar(restauranteId, produtoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionar(@PathVariable Long restauranteId, @RequestBody ProdutoInput produtoInput) {
        Produto produto = produtoInputDisassemble.toDomainObject(produtoInput);

        Produto produtoSalvo = produtoService.salvar(restauranteId, produto);

        return produtoModelAssembler.toModel(produtoSalvo);
    }

    @PutMapping("/{produtoId}")
    public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);

        produtoInputDisassemble.copyToDomainObject(produtoInput, produtoAtual);

        produtoAtual = produtoService.save(produtoAtual);

        return produtoModelAssembler.toModel(produtoAtual);
    }
}
