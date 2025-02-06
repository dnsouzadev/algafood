package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.dnsouzadev.algafood.domain.model.Produto;
import com.dnsouzadev.algafood.domain.model.Restaurante;
import com.dnsouzadev.algafood.domain.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listar(Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        return produtoRepository.findByRestaurante(restaurante);
    }

    @Transactional
    public Produto salvar(Long restauranteId, Produto produto) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        produto.setRestaurante(restaurante);
        produto = produtoRepository.save(produto);

        return produto;
    }

    @Transactional
    public Produto save( Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findProdutoIdByRestauranteId(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }
}
