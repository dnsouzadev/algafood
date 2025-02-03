package com.dnsouzadev.algafood.api.model.input;

public class CidadeInput {
    private String nome;
    private EstadoIdInput estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoIdInput getEstado() {
        return estado;
    }

    public void setEstado(EstadoIdInput estado) {
        this.estado = estado;
    }
}
