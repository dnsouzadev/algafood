package com.dnsouzadev.algafood.domain.repository;

import com.dnsouzadev.algafood.domain.model.FotoProduto;
import com.dnsouzadev.algafood.domain.model.Produto;
import com.dnsouzadev.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries {

    @Query("from Produto where restaurante.id = :restauranteId and id = :produtoId")
    Optional<Produto> findProdutoIdByRestauranteId(@Param("restauranteId") Long restauranteId,
                                                   @Param("produtoId") Long produtoId);

    List<Produto> findByRestaurante(Restaurante restaurante);

    @Query("from Produto where restaurante = :restaurante and ativo = true")
    List<Produto> findAtivoByRestaurante(Restaurante restaurante);

    @Query("select f from FotoProduto f join f.produto p "
            + "where p.restaurante.id = :restauranteId and p.id = :produtoId")
    Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);
}
