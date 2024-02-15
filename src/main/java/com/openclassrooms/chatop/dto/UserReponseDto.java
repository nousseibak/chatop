package com.openclassrooms.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReponseDto implements Serializable {

    @JsonProperty("email")
    private String email;

    @JsonProperty("token")
    private String token;



}
