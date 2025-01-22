package com.dnsouzadev.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problema {
    private LocalDateTime dataHora;
    private String mensagem;

    // Getters e setters
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public static class Builder {
        private LocalDateTime dataHora;
        private String mensagem;

        public Builder dataHora(LocalDateTime dataHora) {
            this.dataHora = dataHora;
            return this;
        }

        public Builder mensagem(String mensagem) {
            this.mensagem = mensagem;
            return this;
        }

        public Problema build() {
            Problema problema = new Problema();
            problema.setDataHora(this.dataHora);
            problema.setMensagem(this.mensagem);
            return problema;
        }
    }
}
