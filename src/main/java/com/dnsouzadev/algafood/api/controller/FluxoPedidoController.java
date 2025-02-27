package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{codigoPedido}")
public class FluxoPedidoController {

    @Autowired
    private FluxoPedidoService fluxoPedidoService;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Class<?> confirmar(@PathVariable String codigoPedido) {
        fluxoPedidoService.confirmar(codigoPedido);
        return null;
    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Class<?> entregar(@PathVariable String codigoPedido) {
        fluxoPedidoService.entregar(codigoPedido);
        return null;
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Class<?> cancelar(@PathVariable String codigoPedido) {
        fluxoPedidoService.cancelar(codigoPedido);
        return null;
    }
}
