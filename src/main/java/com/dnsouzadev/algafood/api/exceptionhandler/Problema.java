package com.dnsouzadev.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

public class Problema {

    private LocalDateTime dataHora;
    private String mensagem;

    public Problema(LocalDateTime dataHora, String mensagem) {
        this.dataHora = dataHora;
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getMensagem() {
        return mensagem;
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
            return new Problema(this.dataHora, this.mensagem);
        }
    }
}
