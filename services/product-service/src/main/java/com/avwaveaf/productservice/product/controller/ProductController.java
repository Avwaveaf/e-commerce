package com.avwaveaf.productservice.product.controller;

import com.avwaveaf.productservice.product.model.ProductPurchaseRequest;
import com.avwaveaf.productservice.product.model.ProductRequest;
import com.avwaveaf.productservice.product.model.ProductResponse;
import com.avwaveaf.productservice.product.model.ProductPurchaseResponse;
import com.avwaveaf.productservice.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest req
    ) {
        return ResponseEntity.ok(service.createProduct(req));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> req
    ) {
        return ResponseEntity.ok(service.purchaseProducts(req));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable("product-id") Integer pid
    ){
        return ResponseEntity.ok(service.getProductById(pid));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

}
