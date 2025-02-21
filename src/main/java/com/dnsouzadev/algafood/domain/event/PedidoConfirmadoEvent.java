package com.dnsouzadev.algafood.domain.event;

import com.dnsouzadev.algafood.domain.model.Pedido;

public class PedidoConfirmadoEvent {

    private Pedido pedido;

    public Pedido getPedido() {
        return pedido;
    }

    public PedidoConfirmadoEvent(Pedido pedido) {
        this.pedido = pedido;
    }
}
