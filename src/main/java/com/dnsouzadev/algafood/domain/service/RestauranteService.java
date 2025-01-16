package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.dnsouzadev.algafood.domain.model.Cozinha;
import com.dnsouzadev.algafood.domain.model.Restaurante;
import com.dnsouzadev.algafood.domain.repository.CozinhaRepository;
import com.dnsouzadev.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long id) {
        return buscarOuFalharRestaurante(id);
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = buscarOuFalharCozinha(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void excluir(Long restauranteId) {
        Restaurante restaurante = buscarOuFalharRestaurante(restauranteId);
        restauranteRepository.delete(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {
        Restaurante restauranteAtual = buscarOuFalharRestaurante(restauranteId);
        Cozinha cozinha = buscarOuFalharCozinha(restaurante.getCozinha().getId());
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro");
        restauranteAtual.setCozinha(cozinha);
        return restauranteRepository.save(restauranteAtual);
    }

    public Restaurante buscarOuFalharRestaurante(Long restauranteId) {
        return restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format("Restaurante de código %d não encontrado", restauranteId))
        );
    }

    public Cozinha buscarOuFalharCozinha(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format("Cozinha de código %d não encontrada", cozinhaId))
        );
    }
}
