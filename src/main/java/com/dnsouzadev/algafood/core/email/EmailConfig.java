package com.dnsouzadev.algafood.core.email;

import com.dnsouzadev.algafood.domain.infrastructure.service.email.FakeEnvioEmailService;
import com.dnsouzadev.algafood.domain.infrastructure.service.email.SmtpEnvioEmailService;
import com.dnsouzadev.algafood.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            default:
                return null;
        }
    }
}
