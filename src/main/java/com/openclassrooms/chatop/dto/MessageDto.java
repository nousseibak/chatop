package com.openclassrooms.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto implements Serializable {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("rental_Id")
    private Integer rentalId;

    @JsonProperty("user_Id")
    private Integer userId;

    @JsonProperty("message")
    private String message;

    @JsonProperty("createdAt")
    private Date createdAt;

    @JsonProperty("updatedAt")
    private Date updatedAt;
}
