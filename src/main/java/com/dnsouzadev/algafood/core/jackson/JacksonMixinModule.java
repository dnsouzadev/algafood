package com.dnsouzadev.algafood.core.jackson;

import com.dnsouzadev.algafood.api.model.mixin.CidadeMixin;
import com.dnsouzadev.algafood.api.model.mixin.CozinhaMixin;
import com.dnsouzadev.algafood.api.model.mixin.RestauranteMixin;
import com.dnsouzadev.algafood.domain.model.Cidade;
import com.dnsouzadev.algafood.domain.model.Cozinha;
import com.dnsouzadev.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

import java.io.Serial;

@Component
public class JacksonMixinModule extends SimpleModule {

    @Serial
    private static final long serialVersionUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
    }
}
