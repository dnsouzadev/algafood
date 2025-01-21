package com.dnsouzadev.algafood.api.exceptionhandler;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class Problema {

    private LocalDateTime dataHora;
    private String mensagem;
}
