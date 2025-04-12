package com.bodega.api.service;

import com.bodega.api.shared.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductService {
    Mono<ProductDto> getProduct(UUID id);
    Flux<ProductDto> getProducts();
    Mono<ProductDto> saveProduct(ProductDto productDto);
}
