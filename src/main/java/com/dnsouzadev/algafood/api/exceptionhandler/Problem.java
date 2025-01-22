package com.dnsouzadev.algafood.api.exceptionhandler;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;

    public static class builder {
        private Integer status;
        private String type;
        private String title;
        private String detail;

        public builder status(Integer status) {
            this.status = status;
            return this;
        }

        public builder type(String type) {
            this.type = type;
            return this;
        }

        public builder title(String title) {
            this.title = title;
            return this;
        }

        public builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Problem build() {
            Problem problem = new Problem();
            problem.status = status;
            problem.type = type;
            problem.title = title;
            problem.detail = detail;

            return problem;
        }
    }

    public Integer getStatus() {
        return status;
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
}