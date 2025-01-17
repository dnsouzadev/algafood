package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.dnsouzadev.algafood.domain.model.Cidade;
import com.dnsouzadev.algafood.domain.model.Estado;
import com.dnsouzadev.algafood.domain.repository.CidadeRepository;
import com.dnsouzadev.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoService estadoService;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscar(Long id) {
        return buscarOuFalhar(id);
    }

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoService.buscarOuFalhar(estadoId);
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Long cidadeId, Cidade cidade) {
        Cidade cidadeAtual = buscarOuFalhar(cidadeId);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return cidadeRepository.save(cidadeAtual);
    }

    public void excluir(Long cidadeId) {
        Cidade cidade = buscarOuFalhar(cidadeId);
        cidadeRepository.delete(cidade);
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Cidade não encontrada")
        );
    }
}