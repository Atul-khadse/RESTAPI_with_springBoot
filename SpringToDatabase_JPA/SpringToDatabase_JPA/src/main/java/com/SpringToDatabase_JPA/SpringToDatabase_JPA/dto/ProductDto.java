package com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String productName;
    private boolean active;
    private Long price;
}
