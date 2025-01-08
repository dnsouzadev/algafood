package com.dnsouzadev.algafood.infrastructure.repository;

import com.dnsouzadev.algafood.domain.model.Cidade;
import com.dnsouzadev.algafood.domain.repository.CidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> listar() {
        return manager.createQuery("from Cidade", Cidade.class)
                .getResultList();
    }

    @Override
    public Cidade buscar(Long id) {
        return manager.find(Cidade.class, id);
    }

    @Override
    public Cidade salvar(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    public Cidade atualizar(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    public void remover(Cidade cidade) {
        cidade = buscar(cidade.getId());
        manager.remove(cidade);
    }

    @Override
    public boolean existsByEstadoId(Long estadoId) {
        String jpql = "SELECT COUNT(c) > 0 FROM Cidade c WHERE c.estado.id = :estadoId";
        return manager.createQuery(jpql, Boolean.class)
                .setParameter("estadoId", estadoId)
                .getSingleResult();
    }
}
