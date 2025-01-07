package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.dnsouzadev.algafood.domain.model.Estado;
import com.dnsouzadev.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ListarEstadoService listarEstadoService;

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    @Transactional
    public Estado atualizar(Estado estado) {
        Estado estadoAtual = listarEstadoService.buscar(estado.getId());
        return estadoRepository.salvar(estadoAtual);
    }

    @Transactional
    public void excluir(Long estadoId) {
        Estado estado = listarEstadoService.buscar(estadoId);
        if (estado == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de estado com código %d", estadoId));
        }
        estadoRepository.remover(estado);
    }

}
