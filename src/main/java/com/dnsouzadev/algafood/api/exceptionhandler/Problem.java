package com.dnsouzadev.algafood.api.exceptionhandler;
import com.fasterxml.jackson.annotation.JsonInclude;

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
    private List<Object> objects;

    private Problem(Integer status, LocalDateTime timestamp, String type, String title, String detail, String userMessage, List<Object> objects) {
        this.status = status;
        this.timestamp = timestamp;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.userMessage = userMessage;
        this.objects = objects;
    }

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

    public List<Object> getObjects() {
        return objects;
    }

    public static class Builder {
        private Integer status;
        private LocalDateTime timestamp;
        private String type;
        private String title;
        private String detail;
        private String userMessage;
        private List<Object> objects;

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

        public Builder objects(List<Object> objects) {
            return this;
        }

        public Problem build() {
            return new Problem(status, timestamp, type, title, detail, userMessage, objects);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Object {

        private String name;
        private String userMessage;

        private Object(String name, String userMessage) {
            this.name = name;
            this.userMessage = userMessage;
        }

        public String getName() {
            return name;
        }

        public String getUserMessage() {
            return userMessage;
        }

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

            public Object build() {
                return new Object(name, userMessage);
            }
        }

        public static Object.Builder builder() {
            return new Object.Builder();
        }
    }
}