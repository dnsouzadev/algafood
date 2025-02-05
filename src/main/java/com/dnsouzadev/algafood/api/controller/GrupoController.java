package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.assembler.GrupoInputDisassemble;
import com.dnsouzadev.algafood.api.assembler.GrupoModelAssembler;
import com.dnsouzadev.algafood.api.model.GrupoModel;
import com.dnsouzadev.algafood.api.model.input.GrupoInput;
import com.dnsouzadev.algafood.domain.model.Grupo;
import com.dnsouzadev.algafood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassemble grupoInputDisassemble;

    @GetMapping
    public List<GrupoModel> listar() {
        return grupoModelAssembler.toCollectionModel(grupoService.listar());
    }

    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        return grupoModelAssembler.toModel(grupoService.buscarOuFalhar(grupoId));
    }

    @PostMapping
    public GrupoModel adicionar(@RequestBody GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassemble.toDomainObject(grupoInput);

        return grupoModelAssembler.toModel(grupoService.salvar(grupo));
    }

    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody GrupoInput grupoInput) {
        Grupo grupoAtual = grupoService.buscarOuFalhar(grupoId);

        grupoInputDisassemble.copyToDomainObject(grupoInput, grupoAtual);

        return grupoModelAssembler.toModel(grupoService.salvar(grupoAtual));
    }

    @DeleteMapping("/{grupoId}")
    public void remover(@PathVariable Long grupoId) {
        grupoService.excluir(grupoId);
    }

}
