package com.SpringToDatabase_JPA.SpringToDatabase_JPA.controller;


import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.CreateOrderDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.OrderDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users/{userID}/orders")
public class OrderController {

private final OrderService orderService;

@PostMapping
public ResponseEntity<OrderDto> createOrder(@PathVariable Long userID, @RequestBody CreateOrderDto createOrderDto){
    return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(userID, createOrderDto));
}

@GetMapping
public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable Long userID){
    return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersByUserId(userID));
}


}
