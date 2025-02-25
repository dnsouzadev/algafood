package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.ResourceUriHelper;
import com.dnsouzadev.algafood.api.assembler.CozinhaModelAssembler;
import com.dnsouzadev.algafood.api.assembler.CozinhaInputDisassemble;
import com.dnsouzadev.algafood.api.model.CozinhaModel;
import com.dnsouzadev.algafood.api.model.input.CozinhaInput;
import com.dnsouzadev.algafood.domain.model.Cozinha;
import com.dnsouzadev.algafood.domain.repository.CozinhaRepository;
import com.dnsouzadev.algafood.domain.service.CozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassemble cozinhaInputDisassemble;

    @GetMapping
    public Page<CozinhaModel> listar(Pageable pageable) {
        List<Cozinha> cozinhas = cozinhaRepository.findAll(pageable).getContent();
        List<CozinhaModel> cozinhasModel = cozinhaModelAssembler.toCollectionModel(cozinhas);

        return new PageImpl<>(cozinhasModel, pageable, cozinhas.size());
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {
        return cozinhaModelAssembler.toModel(cozinhaService.buscarOuFalhar(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassemble.toDomainObject(cozinhaInput);
        ResourceUriHelper.addUriInResponseHeader(cozinha.getId());
        return cozinhaModelAssembler.toModel(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId,
                             @RequestBody CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(cozinhaId);

        cozinhaInputDisassemble.copyToDomainObject(cozinhaInput, cozinhaAtual);

        return cozinhaModelAssembler.toModel(cozinhaService.salvar(cozinhaAtual));
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }
}
