package com.dnsouzadev.algafood.api.assembler;

import com.dnsouzadev.algafood.api.model.input.RestauranteInput;
import com.dnsouzadev.algafood.domain.model.Cozinha;
import com.dnsouzadev.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassemble {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
        // para evitar org.hibernate.HibernateException: identifier of an instance of com.dnsouzadev.algafood.domain.model.Cozinha was altered from 1 to 2
        restaurante.setCozinha(new Cozinha());
        modelMapper.map(restauranteInput, restaurante);
    }
}
