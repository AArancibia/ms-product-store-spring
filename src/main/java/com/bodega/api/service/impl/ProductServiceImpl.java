package com.bodega.api.service.impl;

import com.bodega.api.io.ProductEntity;
import com.bodega.api.repository.ProductRepository;
import com.bodega.api.service.ProductService;
import com.bodega.api.shared.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    ProductRepository repository;

  @Override
  public Mono<ProductDto> getProduct(UUID id) {
    var product = repository.findById(id);
    if (product.isEmpty()) {
      return Mono.error(new RuntimeException("No existe el product con id: " + id));
    }
    return Mono.just(mapper.map(product.get(), ProductDto.class));
  }

  @Override
    public Flux<ProductDto> getProducts() {
        return Flux.fromIterable(repository.findAll())
                .map(productEntity -> mapper.map(productEntity, ProductDto.class))
                .log();
    }

    @Override
    public Mono<ProductDto> saveProduct(ProductDto productDto) {
        return Mono.just(mapper.map(productDto, ProductEntity.class))
                .flatMap(productEntity -> {
                    ProductEntity createdProduct = repository.save(productEntity);
                    return Mono.just(mapper.map(createdProduct, ProductDto.class));
                })
                .log();
    }

}
