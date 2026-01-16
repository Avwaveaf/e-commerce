package com.avwaveaf.productservice.product.service;

import com.avwaveaf.productservice.exception.ProductPurchaseException;
import com.avwaveaf.productservice.product.mapper.ProductMapper;
import com.avwaveaf.productservice.product.model.*;
import com.avwaveaf.productservice.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(@Valid ProductRequest req) {
        var p = mapper.toProduct(req);
        return repository.save(p).getId();
    }

    /**
     * Purchases products; updates stock; builds purchase responses
     */
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> req) {
        var ids = req.stream().map(ProductPurchaseRequest::pid).toList();
        var storedP = repository.findAllByIdInOrderById(ids);
        if(ids.size() != storedP.size()) {
            throw new ProductPurchaseException("One or more Product doesn't exists");
        }
        var storeReq = req.stream().sorted(Comparator.comparing(ProductPurchaseRequest::pid))
                .toList();
        var purchasedP = new ArrayList<ProductPurchaseResponse>();
        // Purchases products; updates stock; builds purchase responses
        for(int i = 0; i<storedP.size(); i++) {
            var p = storedP.get(i);
            var mpReq = storeReq.get(i);
            if(p.getAvailableStock() < mpReq.quantity()) {
                throw new ProductPurchaseException("Not enough stock for product:: " + p.getName());
            }
            var nAvailQty = p.getAvailableStock() - mpReq.quantity();
            p.setAvailableStock(nAvailQty);
            repository.save(p);
            purchasedP.add(mapper.toProductPurchaseResponse(p, mpReq.quantity()));
        }
        return purchasedP;
    }

    public ProductResponse getProductById(Integer pid) {
        return repository.findById(pid)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id:: " + pid));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll().stream().map(mapper::toProductResponse).toList();
    }
}
