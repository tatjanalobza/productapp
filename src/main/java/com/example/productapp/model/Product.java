package com.example.productapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    private Long id;

    @JsonProperty(required = true)
    @NotBlank(message = "The product name cannot be empty")
    private String name;

    @JsonProperty(required = true)
    @Min(value = 0, message = "The current price cannot be less than 0")
    private float currentPrice;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime lastUpdate;

}
