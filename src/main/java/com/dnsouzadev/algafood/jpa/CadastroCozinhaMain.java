package com.dnsouzadev.algafood.jpa;

import com.dnsouzadev.algafood.AlgafoodApplication;
import com.dnsouzadev.algafood.domain.model.Cozinha;
import com.dnsouzadev.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class CadastroCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Brasileira");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Japonesaa");

        cadastroCozinha.salvar(cozinha1);
        cadastroCozinha.salvar(cozinha2);

        System.out.println("Cozinhas cadastradas:");
        cadastroCozinha.listar().forEach(cozinha -> System.out.println(cozinha.getNome()));

    }
}
