package com.dnsouzadev.algafood.api.assembler;

import com.dnsouzadev.algafood.api.model.CozinhaModel;
import com.dnsouzadev.algafood.api.model.RestauranteModel;
import com.dnsouzadev.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {

    public RestauranteModel toModel(Restaurante restaurante) {
        RestauranteModel restauranteModel = new RestauranteModel();
        restauranteModel.setId(restaurante.getId());
        restauranteModel.setNome(restaurante.getNome());
        restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteModel.setCozinha(new CozinhaModel());
        restauranteModel.getCozinha().setId(restaurante.getCozinha().getId());
        restauranteModel.getCozinha().setNome(restaurante.getCozinha().getNome());
        return restauranteModel;
    }

    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }

}
