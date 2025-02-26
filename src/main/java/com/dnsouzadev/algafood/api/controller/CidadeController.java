package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.ResourceUriHelper;
import com.dnsouzadev.algafood.api.assembler.CidadeInputDisassemble;
import com.dnsouzadev.algafood.api.assembler.CidadeModelAssembler;
import com.dnsouzadev.algafood.api.model.CidadeModel;
import com.dnsouzadev.algafood.api.model.input.CidadeInput;
import com.dnsouzadev.algafood.domain.exception.CidadeNaoEncontradaException;
import com.dnsouzadev.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.dnsouzadev.algafood.domain.exception.EstadoNaoEncontradoException;
import com.dnsouzadev.algafood.domain.exception.NegocioException;
import com.dnsouzadev.algafood.domain.model.Cidade;
import com.dnsouzadev.algafood.domain.model.Estado;
import com.dnsouzadev.algafood.domain.repository.CidadeRepository;
import com.dnsouzadev.algafood.domain.service.CidadeService;
import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassemble cidadeInputDisassemble;

    @GetMapping
    public CollectionModel<CidadeModel> listar() {
        return cidadeModelAssembler.toCollectionModel(cidadeService.listar());
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

        return cidadeModelAssembler.toModel(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@RequestBody CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassemble.toDomainObject(cidadeInput);
            CidadeModel cidadeModel =  cidadeModelAssembler.toModel(cidadeService.salvar(cidade));

            ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());

            return cidadeModel;

        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@PathVariable Long cidadeId,
                            @RequestBody CidadeInput cidadeInput) {
        try {
            Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);

            cidadeInputDisassemble.copyToDomainObject(cidadeInput, cidadeAtual);

            return cidadeModelAssembler.toModel(cidadeService.salvar(cidadeAtual));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cidadeService.excluir(cidadeId);
    }

}
