package com.avwaveaf.productservice.product.model;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        double availableStock,
        BigDecimal price,
        String image,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
