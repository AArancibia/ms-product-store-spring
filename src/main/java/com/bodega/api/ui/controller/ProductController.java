package com.bodega.api.ui.controller;

import com.bodega.api.config.BodegaServiceConfig;
import com.bodega.api.ui.model.response.Properties;
import com.bodega.api.service.ProductService;
import com.bodega.api.ui.model.request.ProductRequest;
import com.bodega.api.ui.model.response.ProductResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ModelMapper mapper;
    private final BodegaServiceConfig config;

    @GetMapping
    public Flux<ProductResponse> getProducts() {
        return service.getProducts()
                .map(productDto -> mapper.map(productDto, ProductResponse.class));
    }

    @GetMapping("/properties")
    public String getProperties() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(config.getLanguage(), config.getCountry());
        return ow.writeValueAsString(properties);
    }


    @PostMapping
    public Mono<ProductResponse> createProduct(@RequestBody() ProductRequest productRequest) {
        log.info(productRequest.getName());
        return service.createProduct(productRequest)
          .map(productDto -> mapper.map(productDto, ProductResponse.class));
    }
}
