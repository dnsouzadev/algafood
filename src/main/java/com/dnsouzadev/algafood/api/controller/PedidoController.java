package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.assembler.PedidoModelAssembler;
import com.dnsouzadev.algafood.api.model.PedidoModel;
import com.dnsouzadev.algafood.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @GetMapping
    public List<PedidoModel> listar() {
        return pedidoModelAssembler.toCollectionModel(pedidoService.listar());
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        return pedidoModelAssembler.toModel(pedidoService.buscarOuFalhar(pedidoId));
    }

}
