package com.dnsouzadev.algafood.jpa;

import com.dnsouzadev.algafood.domain.model.Cozinha;
import org.apache.catalina.LifecycleState;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.dnsouzadev.algafood.AlgafoodApplication;

import java.util.List;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        System.out.println("Listando cozinhas");

        List<Cozinha> cozinhas = cadastroCozinha.listar();

        for (Cozinha cozinha : cozinhas) {
            System.out.println(cozinha.getNome());
            System.out.println("ID: " + cozinha.getId());
        }
    }
}
