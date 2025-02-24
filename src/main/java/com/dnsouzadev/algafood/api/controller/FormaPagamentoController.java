package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.assembler.FormaPagamentoInputDisassemble;
import com.dnsouzadev.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.dnsouzadev.algafood.api.model.FormaPagamentoModel;
import com.dnsouzadev.algafood.api.model.input.FormaPagamentoInput;
import com.dnsouzadev.algafood.domain.model.FormaPagamento;
import com.dnsouzadev.algafood.domain.service.FormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDisassemble formaPagamentoInputDisassemble;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoModel>> listar() {
        return ResponseEntity.ok()
                        .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                        .body(formaPagamentoModelAssembler.toCollectionModel(formaPagamentoService.listar()));
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId) {
        return ResponseEntity.ok()
                        .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                        .body(formaPagamentoModelAssembler.toModel(formaPagamentoService.buscarOuFalhar(formaPagamentoId)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@Valid @RequestBody FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoInputDisassemble.toDomainObject(formaPagamentoInput);
        return formaPagamentoModelAssembler.toModel(formaPagamentoService.salvar(formaPagamento));
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel atualizar( @PathVariable Long formaPagamentoId, @Valid @RequestBody FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
        formaPagamentoInputDisassemble.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);
        return formaPagamentoModelAssembler.toModel(formaPagamentoService.salvar(formaPagamentoAtual));
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.excluir(formaPagamentoId);
    }
}
