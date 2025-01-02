package com.dnsouzadev.algafood.domain.repository;

import com.dnsouzadev.algafood.domain.model.Estado;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository {

        List<Estado> listar();
        Estado buscar(Long id);
        Estado salvar(Estado estado);
        void remover(Estado estado);
}
