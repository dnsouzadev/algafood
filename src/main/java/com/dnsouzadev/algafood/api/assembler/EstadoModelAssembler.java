package com.dnsouzadev.algafood.api.assembler;

import com.dnsouzadev.algafood.api.ApiLinks;
import com.dnsouzadev.algafood.api.controller.EstadoController;
import com.dnsouzadev.algafood.api.model.EstadoModel;
import com.dnsouzadev.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ApiLinks apiLinks;

    public EstadoModelAssembler() {
        super(EstadoController.class, EstadoModel.class);
    }

    @Override
    public EstadoModel toModel(Estado estado) {
        EstadoModel estadoModel = createModelWithId(estado.getId(), estado);

        modelMapper.map(estado, estadoModel);

        estadoModel.add(apiLinks.linkToEstados("estados"));

        return estadoModel;
    }

    @Override
    public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(EstadoController.class).withSelfRel());
    }
}
