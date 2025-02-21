package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.model.Pedido;
import com.dnsouzadev.algafood.domain.model.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private EnvioEmailService envioEmailService;

    @Transactional
    public void confirmar(String codigoPedido) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
        pedido.confirmar();

        EnvioEmailService.Mensagem mensagem = new EnvioEmailService.Mensagem.Builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("pedido-confirmado.html")
                .variaveis("pedido", pedido)
                .para(Collections.singleton(pedido.getCliente().getEmail()))
                .build();

        envioEmailService.enviar(mensagem);
    }

    @Transactional
    public void entregar(String codigoPedido) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
        pedido.setStatus(StatusPedido.CANCELADO);
    }

}
