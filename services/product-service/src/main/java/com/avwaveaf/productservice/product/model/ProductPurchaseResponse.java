package com.avwaveaf.productservice.product.model;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer pid,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
