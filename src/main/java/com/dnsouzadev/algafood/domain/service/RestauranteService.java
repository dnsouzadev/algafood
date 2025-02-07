package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.api.model.UsuarioModel;
import com.dnsouzadev.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.dnsouzadev.algafood.domain.model.*;
import com.dnsouzadev.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();

        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
        Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.inativar();
    }

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void abrirRestaurante(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);

        restaurante.abrir();
    }

    @Transactional
    public void fecharRestaurante(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);

        restaurante.fechar();
    }

    public Set<Usuario> listarUsuariosResponsaveis(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);

        return restaurante.getResponsaveis();
    }

    @Transactional
    public void desassociarResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

        restaurante.removerResponsavel(usuario);
    }

    @Transactional
    public void associarResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

        restaurante.adicionarResponsavel(usuario);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

}
