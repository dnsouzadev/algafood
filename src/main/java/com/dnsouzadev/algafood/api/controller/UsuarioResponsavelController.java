package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.ApiLinks;
import com.dnsouzadev.algafood.api.assembler.UsuarioModelAssembler;
import com.dnsouzadev.algafood.api.model.UsuarioModel;
import com.dnsouzadev.algafood.domain.model.Usuario;
import com.dnsouzadev.algafood.domain.service.RestauranteService;
import com.dnsouzadev.algafood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class UsuarioResponsavelController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private ApiLinks apiLinks;

    @GetMapping
    public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Set<Usuario> usuario = restauranteService.listarUsuariosResponsaveis(restauranteId);
        return usuarioModelAssembler.toCollectionModel(usuario)
                .removeLinks()
                .add(apiLinks.linkToResponsaveisRestaurante(restauranteId));
    }

    @PutMapping("/{responsavelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarResponsavel(@PathVariable Long restauranteId, @PathVariable Long responsavelId) {
        restauranteService.associarResponsavel(restauranteId, responsavelId);
    }

    @DeleteMapping("/{responsavelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarResponsavel(@PathVariable Long restauranteId, @PathVariable Long responsavelId) {
        restauranteService.desassociarResponsavel(restauranteId, responsavelId);
    }
}
