package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.assembler.FotoProdutoModelAssembler;
import com.dnsouzadev.algafood.api.model.FotoProdutoModel;
import com.dnsouzadev.algafood.api.model.input.FotoProdutoInput;
import com.dnsouzadev.algafood.domain.model.FotoProduto;
import com.dnsouzadev.algafood.domain.model.Produto;
import com.dnsouzadev.algafood.domain.service.FotoProdutoService;
import com.dnsouzadev.algafood.domain.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private FotoProdutoService fotoProdutoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FotoProdutoModelAssembler fotoProdutoModelAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId,
                                          @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setNomeArquivo(fotoProdutoInput.getArquivo().getOriginalFilename());
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(fotoProdutoInput.getArquivo().getContentType());
        foto.setTamanho(fotoProdutoInput.getArquivo().getSize());

        FotoProduto fotoSalva = fotoProdutoService.salvar(foto, fotoProdutoInput.getArquivo().getInputStream());

        return fotoProdutoModelAssembler.toModel(fotoSalva);
    }

}
