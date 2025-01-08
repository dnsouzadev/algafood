package com.dnsouzadev.algafood.domain.repository;

import com.dnsouzadev.algafood.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query("SELECT COUNT(c) > 0 FROM Cidade c WHERE c.estado.id = :estadoId")
    boolean existsByEstadoId(@Param("estadoId") Long estadoId);
}
