package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.model.Cidade;
import com.dnsouzadev.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarCidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscar(Long id) {
        return cidadeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cidade não encontrada")
        );
    }

}
