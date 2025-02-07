package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.exception.GrupoNaoEncontradoException;
import com.dnsouzadev.algafood.domain.model.Grupo;
import com.dnsouzadev.algafood.domain.model.Permissao;
import com.dnsouzadev.algafood.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class GrupoService {

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private GrupoRepository grupoRepository;

    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long grupoId) {
        grupoRepository.delete(buscarOuFalhar(grupoId));
    }

    public Set<Permissao> buscarPermissoes(Long grupoId) {
        Grupo grupo = buscarOuFalhar(grupoId);

        return grupo.getPermissoes();
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);

        grupo.adicionarPermissao(permissao);
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);

        grupo.removerPermissao(permissao);
    }

    public Grupo buscarOuFalhar(Long grupoId) {
        return grupoRepository.findById(grupoId)
                .orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }

}
