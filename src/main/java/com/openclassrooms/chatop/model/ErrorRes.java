package com.openclassrooms.chatop.model;

import org.springframework.http.HttpStatus;

public class ErrorRes {
    HttpStatus httpStatus;
    String message;

    public ErrorRes() {
    }

    public ErrorRes(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ErrorRes(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}