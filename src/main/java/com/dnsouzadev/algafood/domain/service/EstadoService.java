package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.exception.EntidadeEmUsoException;
import com.dnsouzadev.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.dnsouzadev.algafood.domain.exception.EstadoNaoEncontradoException;
import com.dnsouzadev.algafood.domain.model.Estado;
import com.dnsouzadev.algafood.domain.repository.CidadeRepository;
import com.dnsouzadev.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscar(Long id) {
        return buscarOuFalhar(id);
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado atualizar(Long estadoId, Estado estado) {
        Estado estadoAtual = buscarOuFalhar(estadoId);
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoRepository.save(estadoAtual);
    }

    public void excluir(Long estadoId) {
        Estado estado = buscarOuFalhar(estadoId);

        if (cidadeRepository.existsByEstadoId(estadoId)) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removido, pois está em uso", estadoId));
        }

        estadoRepository.delete(estado);
    }

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }

}
