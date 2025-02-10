package com.dnsouzadev.algafood.core.modelmapper;

import com.dnsouzadev.algafood.api.model.EnderecoModel;
import com.dnsouzadev.algafood.api.model.input.ItemPedidoInput;
import com.dnsouzadev.algafood.domain.model.Endereco;
import com.dnsouzadev.algafood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
                Endereco.class, EnderecoModel.class);

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        enderecoToEnderecoModelTypeMap.<String>addMapping(
                endereco -> endereco.getCidade().getEstado().getNome(),
                (enderecoModel, value) -> enderecoModel.getCidade().setEstado((String) value));


        return modelMapper;
    }

}
