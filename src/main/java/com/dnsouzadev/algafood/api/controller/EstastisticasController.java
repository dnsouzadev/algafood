package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.domain.filter.VendaDiariaFilter;
import com.dnsouzadev.algafood.domain.model.dto.VendaDiaria;
import com.dnsouzadev.algafood.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstastisticasController {

    @Autowired
    private VendaQueryService vendaQueryService;
    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
                                                    @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
    }
}
