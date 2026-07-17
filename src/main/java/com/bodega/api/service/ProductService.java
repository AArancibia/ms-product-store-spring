package com.bodega.api.service;

import com.bodega.api.shared.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Mono<ProductDto> getProduct(UUID id);
    Flux<ProductDto> getProducts();
    Page<ProductDto> getProducts(Pageable pageable); 
    Mono<ProductDto> saveProduct(ProductDto productDto);
}
