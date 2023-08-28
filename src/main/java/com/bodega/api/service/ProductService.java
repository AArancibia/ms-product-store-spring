package com.bodega.api.service;

import com.bodega.api.shared.dto.ProductDto;
import com.bodega.api.ui.model.request.ProductRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductService {
    Flux<ProductDto> getProducts();
    Mono<ProductDto> createProduct(ProductRequest createProductRequest);
}
