package com.dnsouzadev.algafood.domain.infrastructure.repository;

import com.dnsouzadev.algafood.domain.model.FotoProduto;
import com.dnsouzadev.algafood.domain.repository.ProdutoRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public FotoProduto save(FotoProduto foto) {
        return manager.merge(foto);
    }

    @Transactional
    @Override
    public void deleteFoto(FotoProduto foto) {
        manager.remove(foto);
    }

}
