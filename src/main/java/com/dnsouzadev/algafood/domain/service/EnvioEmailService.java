package com.dnsouzadev.algafood.domain.service;

import lombok.NonNull;
import lombok.Singular;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface EnvioEmailService {

    void enviar(Mensagem mensagem);

    class Mensagem {

        @Singular
        private Set<String> destinatarios;

        @NonNull
        private String assunto;

        @NonNull
        private String corpo;

        private Map<String, Object> variaveis = new HashMap<>();

        public Set<String> getDestinatarios() {
            return destinatarios;
        }

        public void setDestinatarios(Set<String> destinatarios) {
            this.destinatarios = destinatarios;
        }

        public String getAssunto() {
            return assunto;
        }

        public void setAssunto(String assunto) {
            this.assunto = assunto;
        }

        public String getCorpo() {
            return corpo;
        }

        public void setCorpo(String corpo) {
            this.corpo = corpo;
        }

        public Map<String, Object> getVariaveis() {
            return variaveis;
        }

        public void setVariaveis(Map<String, Object> variaveis) {
            this.variaveis = variaveis;
        }

        public static class Builder {
            private Mensagem mensagem = new Mensagem();

            public Builder para(Set<String> destinatarios) {
                mensagem.setDestinatarios(destinatarios);
                return this;
            }

            public Builder assunto(String assunto) {
                mensagem.setAssunto(assunto);
                return this;
            }

            public Builder corpo(String corpo) {
                mensagem.setCorpo(corpo);
                return this;
            }

            public Builder variaveis(String chave, Object valor) {
                mensagem.getVariaveis().put(chave, valor);
                return this;
            }

            public Mensagem build() {
                return mensagem;
            }
        }
    }
}
