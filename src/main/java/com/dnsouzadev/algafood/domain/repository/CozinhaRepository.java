package com.dnsouzadev.algafood.domain.repository;

import com.dnsouzadev.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

     List<Cozinha> findByNome(String nome);
}
