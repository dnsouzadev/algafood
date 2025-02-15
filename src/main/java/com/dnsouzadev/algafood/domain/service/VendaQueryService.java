package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.filter.VendaDiariaFilter;
import com.dnsouzadev.algafood.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
