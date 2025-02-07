package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.exception.NegocioException;
import com.dnsouzadev.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.dnsouzadev.algafood.domain.model.Grupo;
import com.dnsouzadev.algafood.domain.model.Usuario;
import com.dnsouzadev.algafood.domain.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EntityManager manager;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {

        manager.detach(usuario);

        Optional<Usuario> usuarioExistente = buscarEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(
                    String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void excluir(Long usuarioId) {
        usuarioRepository.delete(buscarOuFalhar(usuarioId));
    }

    public Optional<Usuario> buscarEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);

        if (!usuario.checkSenha(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(novaSenha);
        usuarioRepository.saveAndFlush(usuario);
    }

    // Grupos associados ao usuário
    public Set<Grupo> buscarGrupos(Long usuarioId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        return usuario.getGrupos();
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = manager.find(Grupo.class, grupoId);

        usuario.adicionarGrupo(grupo);
    }

    @Transactional
    public void desassociarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = manager.find(Grupo.class, grupoId);

        usuario.removerGrupo(grupo);
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

}
