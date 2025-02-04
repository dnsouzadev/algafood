package com.dnsouzadev.algafood.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class RestauranteInput {

        @NotBlank
        private String nome;

        @NotNull
        @PositiveOrZero
        private BigDecimal taxaFrete;

        @Valid
        @NotNull
        private CozinhaIdInput cozinha;

        @Valid
        @NotNull
        private EnderecoInput endereco;

        public String getNome() {
            return nome;
        }

        public BigDecimal getTaxaFrete() {
            return taxaFrete;
        }

        public CozinhaIdInput getCozinha() {
            return cozinha;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setTaxaFrete(BigDecimal taxaFrete) {
            this.taxaFrete = taxaFrete;
        }

        public void setCozinha(CozinhaIdInput cozinha) {
            this.cozinha = cozinha;
        }

        public EnderecoInput getEndereco() {
            return endereco;
        }

        public void setEndereco(EnderecoInput endereco) {
            this.endereco = endereco;
        }
}
