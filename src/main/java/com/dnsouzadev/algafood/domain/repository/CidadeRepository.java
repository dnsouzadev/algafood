package com.dnsouzadev.algafood.domain.repository;

import com.dnsouzadev.algafood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> listar();
    Cidade buscar(Long id);
    Cidade salvar(Cidade cidade);
    Cidade atualizar(Cidade cidade);
    void remover(Cidade cidade);
}
