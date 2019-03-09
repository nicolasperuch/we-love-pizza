package com.github.nicolasperuch.api.dto;

public class ResponseDto {
    private String message;

    public String getMessage() {
        return message;
    }

    public ResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
}
