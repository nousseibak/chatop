package com.openclassrooms.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalOutDto implements Serializable {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surface")
    private Double surface;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("description")
    private String description;

    @JsonProperty("owner_id")
    private Integer owner;

    @JsonProperty("created_at")
    private LocalDate createdAt;

    @JsonProperty("updated_at")
    private LocalDate updatedAt;
}
