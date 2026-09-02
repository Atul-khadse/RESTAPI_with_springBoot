package com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal priceAtPurchase;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public String getProductName() {
        return this.product != null ? this.product.getName() : null;
    }

    // ADD THIS MANUALLY: Satisfies order.setProductName() in your service
    public void setProductName(String productName) {
        if (this.product == null) {
            this.product = new Product();
        }
        this.product.setName(productName);

    }

}
