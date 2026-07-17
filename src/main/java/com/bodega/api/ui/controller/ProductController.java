package com.bodega.api.ui.controller;

import com.bodega.api.shared.dto.ProductDto;
import com.bodega.api.service.ProductService;
import com.bodega.api.ui.model.request.ProductRequest;
import com.bodega.api.ui.model.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @GetMapping("/page/{page}")
    public Page<ProductResponse> getProducts(@PathVariable Integer page, @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return service.getProducts(pageRequest)
                .map(productDto -> mapper.map(productDto, ProductResponse.class));
    }

    @PostMapping
    public Mono<ProductResponse> createProduct(@RequestBody() ProductRequest productRequest) {
        return service.saveProduct(mapper.map(productRequest, ProductDto.class))
          .map(productDto -> mapper.map(productDto, ProductResponse.class));
    }
}
