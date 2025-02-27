package com.dnsouzadev.algafood.api.assembler;

import com.dnsouzadev.algafood.api.ApiLinks;
import com.dnsouzadev.algafood.api.controller.PedidoController;
import com.dnsouzadev.algafood.api.model.PedidoModel;
import com.dnsouzadev.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ApiLinks apiLinks;

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoModel.class);
    }

    @Override
    public PedidoModel toModel(Pedido pedido) {
        PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);

        pedidoModel.add(apiLinks.linkToPedidos());

        if (pedido.podeSerConfirmado())
            pedidoModel.add(apiLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));

        if (pedido.podeSerCancelado())
            pedidoModel.add(apiLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));

        if (pedido.podeSerEntregue())
            pedidoModel.add(apiLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));

        pedidoModel.getRestaurante().add(
                apiLinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoModel.getCliente().add(
                apiLinks.linkToUsuario(pedido.getCliente().getId()));

        pedidoModel.getFormaPagamento().add(
                apiLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

        pedidoModel.getEnderecoEntrega().getCidade().add(
                apiLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

        pedidoModel.getItens().forEach(item -> {
            item.add(apiLinks.linkToProduto(
                    pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"));
        });

        return pedidoModel;
    }

}
