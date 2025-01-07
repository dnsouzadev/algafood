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

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ListarRestauranteService listarRestauranteService;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.salvar(restaurante);
    }

    @Transactional
    public void excluir(Long restauranteId) {
        Restaurante restaurante = listarRestauranteService.existePeloId(restauranteId);
        restauranteRepository.remover(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {
        Restaurante restauranteAtual = listarRestauranteService.existePeloId(restauranteId);
        Cozinha cozinha = cozinhaRepository.buscar(restaurante.getCozinha().getId());
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        restauranteAtual.setCozinha(cozinha);
        return restauranteRepository.salvar(restauranteAtual);
    }








}
