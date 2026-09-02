package com.SpringToDatabase_JPA.SpringToDatabase_JPA.service;

import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.ProductDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.ProductRequestDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities.Product;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.exception.UserNotFoundException;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductDto> getActiveCatalog(){
        return productRepository.findAllByActiveTrueOrderByNameAsc().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }



    @Transactional
    public List<ProductDto> getAllForAdmin(){
        return productRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

//    @CacheEvict(value = "products", key = "#id")
   @Cacheable(value = "products", key = "#id")
    public ProductDto getById(Long id){
        log.info("getting product from DB for id {}", id);
        Product product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new UserNotFoundException("Active product not found with id: " + id));
        return convertToDto(product);
    }

    @Transactional
    public ProductDto create(ProductRequestDto dto){

        Product product = new Product();
        product.setName(dto.getProductName());
//        product.getPrice(dto.getPrice());
        product.setActive(true);

        return convertToDto(productRepository.save(product));

    }

    @CachePut(value = "products", key = "#id")
    @Transactional
    public ProductDto update(Long id, ProductRequestDto dto){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("product not found"));

        product.setName((dto.getProductName()));
        return convertToDto(productRepository.save(product));
    }


    @Transactional
    public void deactivate(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() ->  new UserNotFoundException("product not found") );
        product.setActive(false);
        productRepository.save(product);
    }


    private ProductDto convertToDto(Product product){
        return new ProductDto(product.getId(), product.getName(), product.isActive(),product.getPrice());
    }

}
