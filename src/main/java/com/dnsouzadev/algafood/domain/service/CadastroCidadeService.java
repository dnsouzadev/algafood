package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.model.Cidade;
import com.dnsouzadev.algafood.domain.model.Estado;
import com.dnsouzadev.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Transactional
    public Cidade salvar(Cidade cidade) {
        return cidadeRepository.salvar(cidade);
    }

    @Transactional
    public Cidade atualizar(Long cidadeId, Cidade cidade) {
        Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);
        Estado estado = cidadeAtual.getEstado();
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        cidadeAtual.setEstado(estado);
        return cidadeRepository.salvar(cidadeAtual);
    }

    @Transactional
    public void excluir(Long cidadeId) {
        Cidade cidade = cidadeRepository.buscar(cidadeId);
        cidadeRepository.remover(cidade);
    }


}
