package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.assembler.FotoProdutoModelAssembler;
import com.dnsouzadev.algafood.api.model.FotoProdutoModel;
import com.dnsouzadev.algafood.api.model.input.FotoProdutoInput;
import com.dnsouzadev.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.dnsouzadev.algafood.domain.model.FotoProduto;
import com.dnsouzadev.algafood.domain.model.Produto;
import com.dnsouzadev.algafood.domain.service.FotoProdutoService;
import com.dnsouzadev.algafood.domain.service.FotoStorageService;
import com.dnsouzadev.algafood.domain.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        FotoProduto fotoProduto = fotoProdutoService.buscarOuFalhar(restauranteId, produtoId);

        return fotoProdutoModelAssembler.toModel(fotoProduto);
    }

    @GetMapping
    public ResponseEntity<?> servirFoto(@PathVariable Long restauranteId,
                                                          @PathVariable Long produtoId,
                                                          @RequestHeader(name = "accept") String acceptHeader) {
        try {
            FotoProduto fotoProduto = fotoProdutoService.buscarOuFalhar(restauranteId, produtoId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);

            FotoStorageService.FotoRecuperada fotoRecuperada = fotoProdutoService.recuperar(fotoProduto.getNomeArquivo());

            if (fotoRecuperada.temUrl()) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(mediaTypeFoto)
                        .body(new InputStreamResource(fotoRecuperada.getInputStream()));
            }
        } catch (EntidadeNaoEncontradaException | HttpMediaTypeNotAcceptableException e) {
            return ResponseEntity.notFound().build();
        }
    }

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

    @DeleteMapping
    public void remover(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        fotoProdutoService.excluir(restauranteId, produtoId);
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {

        boolean compativel = mediaTypesAceitas.stream()
                .anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));

        if (!compativel)
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
    }
}
