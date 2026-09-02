package com.SpringToDatabase_JPA.SpringToDatabase_JPA.controller;


import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.CreateOrderDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.OrderDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

private final OrderService orderService;

@PostMapping("/user/{id}")
public ResponseEntity<OrderDto> createOrder(@PathVariable Long id, @Valid @RequestBody CreateOrderDto createOrderDto){
    return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(id, createOrderDto));
}


////USER -> my orders
//    @GetMapping("/my")
//    public ResponseEntity<List<OrderDto>> getMyOrders(){
//    return ResponseEntity.ok(orderService.getMyOrders());
//    }



//USER -> specific orders
@GetMapping("/my/{id}")
public ResponseEntity<List<OrderDto>> getMyOrder(@PathVariable Long id){
    return ResponseEntity.ok(orderService.getOrdersByUserId(id));
}


//ADMIN -> all orders
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(@PathVariable Long id){
    return ResponseEntity.ok(orderService.getAllOrders());
    }

    //ADMIN -> orders by user
    @GetMapping("/user/{id}")
    public ResponseEntity<List<OrderDto>> getOrderByUser(@PathVariable Long id){
    return  ResponseEntity.ok(orderService.getOrdersByUserId(id));
    }

    //ADMIN -> delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
    orderService.deleteOrder(id);
    return ResponseEntity.noContent().build();
    }


}
