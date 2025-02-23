package com.dnsouzadev.algafood.domain.repository;

import com.dnsouzadev.algafood.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {

    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
    List<Pedido> findAll();

    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha where p.codigo = :codigo")
    Optional<Pedido> findByCodigo(String codigo);
}
