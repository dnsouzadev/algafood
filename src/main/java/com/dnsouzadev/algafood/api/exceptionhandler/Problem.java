package com.dnsouzadev.algafood.api.exceptionhandler;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    private Integer status;
    private LocalDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private List<Field> fields;

    // Construtor
    private Problem(Integer status, LocalDateTime timestamp, String type, String title, String detail, String userMessage, List<Field> fields) {
        this.status = status;
        this.timestamp = timestamp;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.userMessage = userMessage;
        this.fields = fields;
    }

    // Getters
    public Integer getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public List<Field> getFields() {
        return fields;
    }

    // Builder
    public static class Builder {
        private Integer status;
        private LocalDateTime timestamp;
        private String type;
        private String title;
        private String detail;
        private String userMessage;
        private List<Field> fields;

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder userMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        public Builder fields(List<Field> fields) {
            this.fields = fields;
            return this;
        }

        public Problem build() {
            return new Problem(status, timestamp, type, title, detail, userMessage, fields);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // Classe interna Field
    public static class Field {

        private String name;
        private String userMessage;

        // Construtor
        private Field(String name, String userMessage) {
            this.name = name;
            this.userMessage = userMessage;
        }

        // Getters
        public String getName() {
            return name;
        }

        public String getUserMessage() {
            return userMessage;
        }

        // Builder para Field
        public static class Builder {
            private String name;
            private String userMessage;

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder userMessage(String userMessage) {
                this.userMessage = userMessage;
                return this;
            }

            public Field build() {
                return new Field(name, userMessage);
            }
        }

        public static Field.Builder builder() {
            return new Field.Builder();
        }
    }
}