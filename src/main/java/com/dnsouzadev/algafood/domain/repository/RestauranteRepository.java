package com.dnsouzadev.algafood.domain.repository;

import com.dnsouzadev.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository {

    List<Restaurante> listar();
    Restaurante buscar(Long id);
    Restaurante salvar(Restaurante restaurante);
    void remover(Restaurante restaurante);
    Restaurante atualizar(Restaurante restaurante);
}
