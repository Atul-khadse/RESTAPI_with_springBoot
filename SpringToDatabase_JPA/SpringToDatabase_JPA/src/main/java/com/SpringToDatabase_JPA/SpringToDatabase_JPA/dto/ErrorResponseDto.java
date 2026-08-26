package com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponseDto {
    private String code;
    private String message;

}
