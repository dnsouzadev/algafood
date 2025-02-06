package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.assembler.UsuarioInputDisassemble;
import com.dnsouzadev.algafood.api.assembler.UsuarioModelAssembler;
import com.dnsouzadev.algafood.api.model.UsuarioModel;
import com.dnsouzadev.algafood.api.model.input.UsuarioInput;
import com.dnsouzadev.algafood.api.model.input.UsuarioSemSenhaInput;
import com.dnsouzadev.algafood.api.model.input.UsuarioTrocaDeSenhaInput;
import com.dnsouzadev.algafood.domain.model.Usuario;
import com.dnsouzadev.algafood.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassemble usuarioInputDisassemble;

    @GetMapping
    public List<UsuarioModel> listar() {
        return usuarioModelAssembler.toCollectionModel(usuarioService.listar());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        return usuarioModelAssembler.toModel(usuarioService.buscarOuFalhar(usuarioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
        return usuarioModelAssembler.toModel(usuarioService.salvar(usuarioInputDisassemble.toDomainObject(usuarioInput)));
    }

    @PutMapping("{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioTrocaDeSenhaInput usuarioTrocaDeSenhaInput) {
        usuarioService.alterarSenha(usuarioId, usuarioTrocaDeSenhaInput.getSenhaAtual(), usuarioTrocaDeSenhaInput.getNovaSenha());
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioSemSenhaInput usuarioInput) {
        Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);

        usuarioInputDisassemble.copyToDomainObject(usuarioInput, usuarioAtual);

        return usuarioModelAssembler.toModel(usuarioService.salvar(usuarioAtual));
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId) {
        usuarioService.excluir(usuarioId);
    }
}
