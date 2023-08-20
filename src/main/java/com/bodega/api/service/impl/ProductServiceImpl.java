package com.bodega.api.service.impl;

import com.bodega.api.io.ProductEntity;
import com.bodega.api.repository.ProductRepository;
import com.bodega.api.service.ProductService;
import com.bodega.api.shared.dto.ProductDto;
import com.bodega.api.ui.model.request.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    ProductRepository repository;

    @Override
    public Flux<ProductDto> getProducts() {
        return Flux.fromIterable(repository.findAll())
                .map(productEntity -> mapper.map(productEntity, ProductDto.class))
                .log();
    }

    @Override
    public Mono<ProductDto> createProduct(ProductRequest createProductRequest) {
        return Mono.just(mapper.map(createProductRequest, ProductEntity.class))
                .flatMap(productEntity -> {
                    ProductEntity createdProduct = repository.save(productEntity);
                    return Mono.just(mapper.map(createdProduct, ProductDto.class));
                })
                .log();
    }

}
