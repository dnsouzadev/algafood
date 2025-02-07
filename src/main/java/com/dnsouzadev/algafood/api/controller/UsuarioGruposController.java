package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.assembler.GrupoModelAssembler;
import com.dnsouzadev.algafood.api.model.GrupoModel;
import com.dnsouzadev.algafood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGruposController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @GetMapping
    public List<GrupoModel> listar(@PathVariable Long usuarioId) {
        return grupoModelAssembler.toCollectionModel(usuarioService.buscarGrupos(usuarioId));
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.associarGrupo(usuarioId, grupoId);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarGrupo(usuarioId, grupoId);
    }


}
