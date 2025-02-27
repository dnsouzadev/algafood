package com.dnsouzadev.algafood.api.assembler;

import com.dnsouzadev.algafood.api.ApiLinks;
import com.dnsouzadev.algafood.api.controller.CozinhaController;
import com.dnsouzadev.algafood.api.model.CozinhaModel;
import com.dnsouzadev.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ApiLinks apiLinks;

    public CozinhaModelAssembler() {
        super(CozinhaController.class, CozinhaModel.class);
    }

    @Override
    public CozinhaModel toModel(Cozinha cozinha) {
        CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);

        modelMapper.map(cozinha, cozinhaModel);

        cozinhaModel.add(apiLinks.linkToCozinhas("cozinhas"));

        return cozinhaModel;
    }

}
