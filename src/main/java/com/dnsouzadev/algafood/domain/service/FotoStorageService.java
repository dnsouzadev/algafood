package com.dnsouzadev.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    FotoRecuperada recuperar(String nomeArquivo);

    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    default void substituir(String nomeArquivoExistente, NovaFoto novaFoto) {
        this.armazenar(novaFoto);

        if (nomeArquivoExistente != null) {
            this.remover(nomeArquivoExistente);
        }
    }

    default String gerarNomeArquivo(String nomeArquivo) {
        return UUID.randomUUID() + "_" + nomeArquivo;
    }

    class NovaFoto {
        private String nomeArquivo;
        private String contentType;
        private InputStream inputStream;


        public String getNomeArquivo() {
            return nomeArquivo;
        }

        public InputStream getInputStream() {
            return inputStream;
        }

        public String getContentType() {
            return contentType;
        }

        static class Builder {
            private NovaFoto novaFoto = new NovaFoto();

            public Builder comNomeArquivo(String nomeArquivo) {
                novaFoto.nomeArquivo = nomeArquivo;
                return this;
            }

            public Builder comInputStream(InputStream inputStream) {
                novaFoto.inputStream = inputStream;
                return this;
            }

            public Builder comContentType(String contentType) {
                novaFoto.contentType = contentType;
                return this;
            }

            public NovaFoto build() {
                return novaFoto;
            }
        }
    }

    class FotoRecuperada {
        private InputStream inputStream;
        private String url;

        public InputStream getInputStream() {
            return inputStream;
        }

        public String getUrl() {
            return url;
        }

        public boolean temUrl() {
            return url != null;
        }

        public boolean temInputStream() {
            return inputStream != null;
        }

        public static class Builder {
            private FotoRecuperada fotoRecuperada = new FotoRecuperada();

            public Builder comInputStream(InputStream inputStream) {
                fotoRecuperada.inputStream = inputStream;
                return this;
            }

            public Builder comUrl(String url) {
                fotoRecuperada.url = url;
                return this;
            }

            public FotoRecuperada build() {
                return fotoRecuperada;
            }
        }
    }
}
