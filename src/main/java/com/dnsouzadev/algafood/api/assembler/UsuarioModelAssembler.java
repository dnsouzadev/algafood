package com.dnsouzadev.algafood.api.assembler;

import com.dnsouzadev.algafood.api.ApiLinks;
import com.dnsouzadev.algafood.api.controller.UsuarioController;
import com.dnsouzadev.algafood.api.controller.UsuarioGruposController;
import com.dnsouzadev.algafood.api.model.UsuarioModel;
import com.dnsouzadev.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ApiLinks apiLinks;

    public UsuarioModelAssembler() {
        super(UsuarioController.class, UsuarioModel.class);
    }

    @Override
    public UsuarioModel toModel(Usuario usuario) {
        UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);

        modelMapper.map(usuario, usuarioModel);

        usuarioModel.add(apiLinks.linkToUsuarios("usuarios"));
        usuarioModel.add(apiLinks.linkToGruposUsuario(usuarioModel.getId(), "grupos-usuario"));

        return usuarioModel;
    }

    @Override
    public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities)
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).listar()).withSelfRel());
    }
}
