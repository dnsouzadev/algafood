package com.dnsouzadev.algafood.domain.event;

import com.dnsouzadev.algafood.domain.model.Pedido;

public class PedidoCanceladoEvent {

    private Pedido pedido;

    public Pedido getPedido() {
        return pedido;
    }

    public PedidoCanceladoEvent(Pedido pedido) {
        this.pedido = pedido;
    }

}
