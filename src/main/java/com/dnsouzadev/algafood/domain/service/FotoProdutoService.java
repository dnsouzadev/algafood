package com.dnsouzadev.algafood.domain.service;

import com.dnsouzadev.algafood.domain.model.FotoProduto;
import com.dnsouzadev.algafood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class FotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
        String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(
                foto.getProduto().getRestaurante().getId(), foto.getProduto().getId());

        fotoExistente.ifPresent(fotoProduto -> produtoRepository.delete(fotoProduto));

        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        FotoStorageService.NovaFoto novaFoto = new FotoStorageService.NovaFoto.Builder()
                .comNomeArquivo(nomeNovoArquivo)
                .comInputStream(dadosArquivo)
                .build();

        fotoStorageService.armazenar(novaFoto);

        return foto;
    }
}
