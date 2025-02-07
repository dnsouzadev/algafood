package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.exception.PermissaoNaoEncontradaException;
import com.dnsouzadev.algafood.domain.model.Permissao;
import com.dnsouzadev.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }

}
