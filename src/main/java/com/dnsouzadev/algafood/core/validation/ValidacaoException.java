package com.dnsouzadev.algafood.core.validation;


import org.springframework.validation.BindingResult;

import java.io.Serial;

public class ValidacaoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final BindingResult bindingResult;

    public ValidacaoException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

}