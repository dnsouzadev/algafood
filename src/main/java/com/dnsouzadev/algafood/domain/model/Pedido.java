package com.dnsouzadev.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;

    @Embedded
    private Endereco enderecoEntrega;

    private StatusPedido status;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataEntrega;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(Long id, BigDecimal subtotal, BigDecimal taxaFrete, Endereco enderecoEntrega, BigDecimal valorTotal, StatusPedido status, LocalDateTime dataCriacao, LocalDateTime dataConfirmacao, LocalDateTime dataCancelamento, LocalDateTime dataEntrega, FormaPagamento formaPagamento, Restaurante restaurante, Usuario cliente, List<ItemPedido> itens) {
        this.id = id;
        this.subtotal = subtotal;
        this.taxaFrete = taxaFrete;
        this.enderecoEntrega = enderecoEntrega;
        this.valorTotal = valorTotal;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.dataConfirmacao = dataConfirmacao;
        this.dataCancelamento = dataCancelamento;
        this.dataEntrega = dataEntrega;
        this.formaPagamento = formaPagamento;
        this.restaurante = restaurante;
        this.cliente = cliente;
        this.itens = itens;
    }

    public Pedido() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(LocalDateTime dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public LocalDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(subtotal, pedido.subtotal) && Objects.equals(taxaFrete, pedido.taxaFrete) && Objects.equals(valorTotal, pedido.valorTotal) && Objects.equals(enderecoEntrega, pedido.enderecoEntrega) && status == pedido.status && Objects.equals(dataCriacao, pedido.dataCriacao) && Objects.equals(dataConfirmacao, pedido.dataConfirmacao) && Objects.equals(dataCancelamento, pedido.dataCancelamento) && Objects.equals(dataEntrega, pedido.dataEntrega) && Objects.equals(formaPagamento, pedido.formaPagamento) && Objects.equals(restaurante, pedido.restaurante) && Objects.equals(cliente, pedido.cliente) && Objects.equals(itens, pedido.itens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subtotal, taxaFrete, valorTotal, enderecoEntrega, status, dataCriacao, dataConfirmacao, dataCancelamento, dataEntrega, formaPagamento, restaurante, cliente, itens);
    }
}