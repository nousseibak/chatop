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

    @JsonProperty("rentalId")
    private Integer rentalId;

    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("message")
    private String message;

    @JsonProperty("createdAt")
    private Date createdAt;

    @JsonProperty("updatedAt")
    private Date updatedAt;
}
