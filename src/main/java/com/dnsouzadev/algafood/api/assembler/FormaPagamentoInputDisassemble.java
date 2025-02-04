package com.dnsouzadev.algafood.api.assembler;

import com.dnsouzadev.algafood.api.model.input.FormaPagamentoInput;
import com.dnsouzadev.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoInputDisassemble {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
        return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
    }

    public void copyToDomainObject(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamentoAtual) {
        modelMapper.map(formaPagamentoInput, formaPagamentoAtual);
    }

}
