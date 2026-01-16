package com.avwaveaf.productservice.product.mapper;

import com.avwaveaf.productservice.product.model.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest req) {
        return Product.builder()
                .id(req.id())
                .name(req.name())
                .price(req.price())
                .description(req.description())
                .availableStock(req.availableStock())
                .image(req.image())
                .category(
                        Category.builder()
                                .id(req.categoryId())
                                .build()
                )
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableStock(),
                product.getPrice(),
                product.getImage(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product p,  double quantity) {
        return new ProductPurchaseResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                quantity
        );
    }
}
