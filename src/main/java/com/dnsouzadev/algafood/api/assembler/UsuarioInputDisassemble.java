package com.dnsouzadev.algafood.api.assembler;

import com.dnsouzadev.algafood.api.model.UsuarioModel;
import com.dnsouzadev.algafood.api.model.input.UsuarioInput;
import com.dnsouzadev.algafood.api.model.input.UsuarioSemSenhaInput;
import com.dnsouzadev.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassemble {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModel toUsuarioModel(UsuarioInput usuarioInput) {
        return modelMapper.map(usuarioInput, UsuarioModel.class);
    }

    public Usuario toDomainObject(UsuarioInput usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioSemSenhaInput usuarioInput, Usuario usuario) {
        modelMapper.map(usuarioInput, usuario);
    }

}
