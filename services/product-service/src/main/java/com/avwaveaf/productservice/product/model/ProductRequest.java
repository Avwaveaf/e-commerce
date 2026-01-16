package com.avwaveaf.productservice.product.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Name cannot be null")
        String name,
        @NotNull(message = "Description cannot be null")
        String description,
        @Positive(message = "Available stock must be positive")
        double availableStock,
        @Positive(message = "Price must be positive")
        BigDecimal price,
        String image,
        @NotNull(message = "Category cannot be null")
        Integer categoryId
) {
}
