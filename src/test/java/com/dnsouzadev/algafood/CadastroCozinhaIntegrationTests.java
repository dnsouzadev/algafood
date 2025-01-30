package com.dnsouzadev.algafood;

import com.dnsouzadev.algafood.domain.model.Cozinha;
import com.dnsouzadev.algafood.domain.service.CozinhaService;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

    @Autowired
    private CozinhaService cozinhaService;
    @Test
    public void testarCadastroCozinhaComSucesso() {
        // cenario
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        // acao
        novaCozinha = cozinhaService.salvar(novaCozinha);

        // validacao
        Assertions.assertThat(novaCozinha).isNotNull();
        Assertions.assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    public void deveFalharAoCadastrarCozinhaSemNome() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> cozinhaService.salvar(cozinha));
    }

}
