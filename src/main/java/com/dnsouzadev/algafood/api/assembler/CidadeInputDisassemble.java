package com.dnsouzadev.algafood.api.assembler;

import com.dnsouzadev.algafood.api.model.input.CidadeInput;
import com.dnsouzadev.algafood.domain.model.Cidade;
import com.dnsouzadev.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDisassemble {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
        // para evitar o erro: identifier of an instance of com.dnsouzadev.algafood.domain.model.Estado was altered from 3 to 1
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeInput, cidade);
    }

}
