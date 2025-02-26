package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.assembler.EstadoInputDisassemble;
import com.dnsouzadev.algafood.api.assembler.EstadoModelAssembler;
import com.dnsouzadev.algafood.api.model.EstadoModel;
import com.dnsouzadev.algafood.api.model.input.EstadoInput;
import com.dnsouzadev.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.dnsouzadev.algafood.domain.model.Estado;
import com.dnsouzadev.algafood.domain.repository.EstadoRepository;
import com.dnsouzadev.algafood.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassemble estadoInputDisassemble;

    @GetMapping
    public CollectionModel<EstadoModel> listar() {
        return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
    }

    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Long estadoId) {
        return estadoModelAssembler.toModel(estadoService.buscarOuFalhar(estadoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody EstadoInput estadoInput) {
        Estado estado = estadoInputDisassemble.toDomainObject(estadoInput);
        return estadoModelAssembler.toModel(estadoService.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Long estadoId,
                            @RequestBody EstadoInput estadoInput) {
        Estado estadoAtual = estadoService.buscarOuFalhar(estadoId);

        estadoInputDisassemble.copyToDomainObject(estadoInput, estadoAtual);

        return estadoModelAssembler.toModel(estadoService.salvar(estadoAtual));
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        estadoService.excluir(estadoId);
    }

}
