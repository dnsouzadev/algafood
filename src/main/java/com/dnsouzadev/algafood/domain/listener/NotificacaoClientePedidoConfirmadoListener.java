package com.dnsouzadev.algafood.domain.listener;

import com.dnsouzadev.algafood.domain.event.PedidoConfirmadoEvent;
import com.dnsouzadev.algafood.domain.model.Pedido;
import com.dnsouzadev.algafood.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    @EventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
        Pedido pedido = event.getPedido();

        var mensagem = new EnvioEmailService.Mensagem.Builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("pedido-confirmado.html")
                .variaveis("pedido", pedido)
                .para(Collections.singleton(pedido.getCliente().getEmail()))
                .build();

        envioEmailService.enviar(mensagem);

        System.out.println("Notificando cliente " + event.getPedido().getCliente().getNome()
                + " <" + event.getPedido().getCliente().getEmail() + ">");
        System.out.println("Seu pedido de código " + event.getPedido().getCodigo() + " foi confirmado!");
    }

}
