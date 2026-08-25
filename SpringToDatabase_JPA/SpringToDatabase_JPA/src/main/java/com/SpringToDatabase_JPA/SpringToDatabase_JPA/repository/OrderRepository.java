package com.SpringToDatabase_JPA.SpringToDatabase_JPA.repository;

import com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrderByUserId(Long userId);


}
