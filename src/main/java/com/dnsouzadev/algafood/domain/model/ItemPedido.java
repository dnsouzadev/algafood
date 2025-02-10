package com.dnsouzadev.algafood.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private Integer quantidade;
    private String observacao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    public ItemPedido(Long id, BigDecimal precoUnitario, BigDecimal precoTotal, Integer quantidade, String observacao, Pedido pedido, Produto produto) {
        this.id = id;
        this.precoUnitario = precoUnitario;
        this.precoTotal = precoTotal;
        this.quantidade = quantidade;
        this.observacao = observacao;
        this.pedido = pedido;
        this.produto = produto;
    }

    public ItemPedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id) && Objects.equals(precoUnitario, that.precoUnitario) && Objects.equals(precoTotal, that.precoTotal) && Objects.equals(quantidade, that.quantidade) && Objects.equals(observacao, that.observacao) && Objects.equals(pedido, that.pedido) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, precoUnitario, precoTotal, quantidade, observacao, pedido, produto);
    }

    public void calcularPrecoTotal() {
        BigDecimal precoUnitario = this.getPrecoUnitario();
        Integer quantidade = this.getQuantidade();

        if (precoUnitario == null) {
            precoUnitario = BigDecimal.ZERO;
        }

        if (quantidade == null) {
            quantidade = 0;
        }

        this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
    }
}
