package com.bodega.api.ui.controller;

import com.bodega.api.service.ProductService;
import com.bodega.api.shared.dto.ProductDto;
import com.bodega.api.ui.model.request.ProductRequest;
import com.bodega.api.ui.model.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ModelMapper mapper;

    @GetMapping
    public Flux<ProductResponse> getProducts() {
        return service.getProducts()
                .map(productDto -> mapper.map(productDto, ProductResponse.class));
    }

    @PostMapping
    public Mono<ProductResponse> createProduct(@RequestBody() ProductRequest productRequest) {
        log.info(productRequest.getName());
        return service.createProduct(productRequest)
            .map(productDto -> mapper.map(productDto, ProductResponse.class));
    }
}
