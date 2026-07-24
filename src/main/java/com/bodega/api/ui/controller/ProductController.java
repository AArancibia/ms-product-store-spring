package com.bodega.api.ui.controller;

import com.bodega.api.shared.dto.ProductDto;
import com.bodega.api.service.ProductService;
import com.bodega.api.ui.model.request.ProductRequest;
import com.bodega.api.ui.model.response.ProductResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Tag(name = "Product", description = "Endpoint methods for products feature")
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ModelMapper mapper;

    @Operation(summary = "List of products")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Get all products"),
    		@ApiResponse(responseCode = "400", description = "No products found")
    })
    @GetMapping
    public Flux<ProductResponse> getProducts() {
        return service.getProducts()
                .map(productDto -> mapper.map(productDto, ProductResponse.class));
    }

    @Operation(summary = "List of products with pagination")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Get all products paginated"),
    		@ApiResponse(responseCode = "400", description = "No products found")
    })
    @GetMapping("/page/{page}")
    public Page<ProductResponse> getProducts(@PathVariable Integer page, @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return service.getProducts(pageRequest)
                .map(productDto -> mapper.map(productDto, ProductResponse.class));
    }

    @Operation(summary = "Create product")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "201", description = "Product created"),
    		@ApiResponse(responseCode = "400", description = "No products found")
    })
    @PostMapping
    public Mono<ProductResponse> createProduct(@RequestBody() ProductRequest productRequest) {
        return service.saveProduct(mapper.map(productRequest, ProductDto.class))
          .map(productDto -> mapper.map(productDto, ProductResponse.class));
    }
}
