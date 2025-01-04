package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.model.Cozinha;
import com.dnsouzadev.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    public Cozinha buscarOuFalhar(Long cozinhaId) {
        if (cozinhaId == null) {
            throw new IllegalArgumentException("O ID da cozinha não pode ser nulo.");
        }
        return cozinhaRepository.buscar(cozinhaId);
    }

    public void excluir(Long cozinhaId) {
        Cozinha cozinha = buscarOuFalhar(cozinhaId);
        cozinhaRepository.remover(cozinha);
    }




}
