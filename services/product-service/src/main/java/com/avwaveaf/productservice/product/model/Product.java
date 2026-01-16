package com.avwaveaf.productservice.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private double availableStock;
    private BigDecimal price;
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
