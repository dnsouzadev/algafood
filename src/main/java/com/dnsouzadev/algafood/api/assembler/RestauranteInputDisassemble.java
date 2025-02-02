package com.dnsouzadev.algafood.api.assembler;

import com.dnsouzadev.algafood.api.model.input.RestauranteInput;
import com.dnsouzadev.algafood.domain.model.Cozinha;
import com.dnsouzadev.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassemble {

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());

        restaurante.setCozinha(cozinha);

        return restaurante;
    }
}
