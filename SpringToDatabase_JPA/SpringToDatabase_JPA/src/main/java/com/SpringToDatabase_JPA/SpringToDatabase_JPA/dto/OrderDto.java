package com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto;

import com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String productName;
    private UserDto user;

}
