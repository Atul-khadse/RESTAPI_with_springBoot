package com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {

    @NotBlank(message = "Product name is required")
    private String productName;
    private Long price;
}
