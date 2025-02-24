package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.dnsouzadev.algafood.domain.model.FormaPagamento;
import com.dnsouzadev.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.findAll();
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void excluir(Long formaPagamentoId) {
        formaPagamentoRepository.delete(buscarOuFalhar(formaPagamentoId));
        formaPagamentoRepository.flush();
    }

    public OffsetDateTime getDataAtualizacao() {
        return formaPagamentoRepository.getDataAtualizacao();
    }

    public OffsetDateTime getDataAtualizacaoById(Long formaPagamentoId) {
        return formaPagamentoRepository.getDataAtualizacaoById(formaPagamentoId);
    }

    public FormaPagamento buscarOuFalhar(Long formaPagamentoId) {
        return formaPagamentoRepository.findById(formaPagamentoId)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
    }

}
