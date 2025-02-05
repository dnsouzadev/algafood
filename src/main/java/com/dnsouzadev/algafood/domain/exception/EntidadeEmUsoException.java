package com.dnsouzadev.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends NegocioException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoException(String mensagem) {
            super(mensagem);
    }
}
