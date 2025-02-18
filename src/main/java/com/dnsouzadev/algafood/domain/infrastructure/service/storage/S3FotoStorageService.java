package com.dnsouzadev.algafood.domain.infrastructure.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dnsouzadev.algafood.core.storage.StorageProperties;
import com.dnsouzadev.algafood.domain.exception.StorageException;
import com.dnsouzadev.algafood.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Primary
public class S3FotoStorageService implements FotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public InputStream recuperar(String nomeArquivo) {
        return null;
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());

            var objectMetaData = new ObjectMetadata();
            objectMetaData.setContentType(novaFoto.getContentType());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    caminhoArquivo, novaFoto.getInputStream(),
                    objectMetaData
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar arquivo na Amazon S3.", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {

    }

    private String getCaminhoArquivo(String nomeArquivo) {
        return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
    }
}
