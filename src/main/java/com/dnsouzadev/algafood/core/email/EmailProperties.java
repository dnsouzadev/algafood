package com.dnsouzadev.algafood.core.email;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

    @NotBlank
    private String remetente;

    private Implementacao impl = Implementacao.FAKE;

    public enum Implementacao {
        SMTP, FAKE
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public Implementacao getImpl() {
        return impl;
    }

    public void setImpl(Implementacao impl) {
        this.impl = impl;
    }
}
