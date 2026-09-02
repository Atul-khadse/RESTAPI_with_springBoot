package com.SpringToDatabase_JPA.SpringToDatabase_JPA.service;


import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.CreateOrderDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.OrderDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.UserDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities.Order;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities.User;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.exception.UserNotFoundException;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.repository.OrderRepository;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;


    @Transactional
    public OrderDto createOrder(Long userID, CreateOrderDto createOrderDto) {
        User user = userRepository.findById(userID).orElseThrow(() -> new UserNotFoundException("User not found with id"));
        Order order = new Order();
        order.setUser(user);
        order.setProductName(createOrderDto.getProductName());

        Order saveOrder = orderRepository.save(order);
        return new OrderDto(saveOrder.getId(),saveOrder.getProductName(),
                new UserDto(saveOrder.getUser().getId(), saveOrder.getUser().getName(),saveOrder.getUser().getEmail()));

    }

    public List<OrderDto> getOrdersByUserId(Long userID) {
        List<Order> orders = orderRepository.findOrderByUserId(userID);
        List<OrderDto> orderDtos = new ArrayList<>();

        orders.forEach( order -> {
            OrderDto orderDto = new OrderDto(order.getId(), order.getProductName(),
                    new UserDto(order.getUser().getId(), order.getUser().getName(),order.getUser().getEmail()));
            orderDtos.add(orderDto);
        });
        return orderDtos;
    }




    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();

        orders.forEach(order -> {
            OrderDto orderDto = new OrderDto(
                    order.getId(),
                    order.getProductName(),
                    new UserDto(
                            order.getUser().getId(),
                            order.getUser().getName(),
                            order.getUser().getEmail()
                    )
            );
            orderDtos.add(orderDto);
        });
        return orderDtos;
    }






    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new UserNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }




}
