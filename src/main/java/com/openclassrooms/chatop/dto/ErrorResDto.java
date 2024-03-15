package com.openclassrooms.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ErrorResDto implements Serializable {

    @JsonProperty("message")
    private String message;

    public ErrorResDto() {
    }

    public ErrorResDto(String message) {
        this.message = message;
    }
}
