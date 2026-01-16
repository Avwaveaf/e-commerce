package com.avwaveaf.productservice.product.model;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product id cannot be null")
        Integer pid,
        @NotNull(message = "Quantity cannot be null")
        double quantity
) {
}
