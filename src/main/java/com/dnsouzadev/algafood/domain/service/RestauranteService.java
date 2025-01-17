package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.dnsouzadev.algafood.domain.exception.NegocioException;
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
    private CozinhaService cozinhaService;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long id) {
        return buscarOuFalharRestaurante(id);
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);

        restaurante.setCozinha(cozinha);

        try {
            return restauranteRepository.save(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Transactional
    public void excluir(Long restauranteId) {
        Restaurante restaurante = buscarOuFalharRestaurante(restauranteId);
        restauranteRepository.delete(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {
        Restaurante restauranteAtual = buscarOuFalharRestaurante(restauranteId);
        Cozinha cozinha = cozinhaService.buscarOuFalhar(restaurante.getCozinha().getId());
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro");
        restauranteAtual.setCozinha(cozinha);
        try {
            return restauranteRepository.save(restauranteAtual);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public Restaurante buscarOuFalharRestaurante(Long restauranteId) {
        return restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format("Restaurante de código %d não encontrado", restauranteId))
        );
    }

}
