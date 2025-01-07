package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.dnsouzadev.algafood.domain.model.Estado;
import com.dnsouzadev.algafood.domain.repository.CidadeRepository;
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

    @Autowired
    private CidadeRepository cidadeRepository;

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    @Transactional
    public Estado atualizar(Long estadoId, Estado estado) {
        Estado estadoAtual = listarEstadoService.buscar(estadoId);
        return estadoRepository.salvar(estadoAtual);
    }

    @Transactional
    public void excluir(Long estadoId) {
        Estado estado = listarEstadoService.buscar(estadoId);

        if (cidadeRepository.existsByEstadoId(estadoId)) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Estado de código %d não pode ser removido, pois está em uso", estadoId));
        }

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de estado com código %d", estadoId));
        }
        estadoRepository.remover(estado);
    }

}
