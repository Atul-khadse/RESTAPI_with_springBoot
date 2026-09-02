package com.SpringToDatabase_JPA.SpringToDatabase_JPA.repository;

import com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

List<Product> findAllByActiveTrueOrderByNameAsc();

Optional<Product> findByIdAndActiveTrue(Long id);

boolean existsByNameIgnoreCase(String name);

boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

}
