package com.bodega.api.ui.controller;

import com.bodega.api.io.ProductEntity;
import com.bodega.api.service.ProductService;
import com.bodega.api.shared.dto.ProductDto;
import com.bodega.api.shared.report.SaleReport;
import com.bodega.api.ui.model.request.ProductRequest;
import com.bodega.api.ui.model.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ModelMapper mapper;

    @GetMapping
    public List<ProductResponse> getProducts() {
        List<ProductDto> productDtos = service.getProducts();
        ArrayList<ProductResponse> products = new ArrayList<>();
        for (ProductDto productDto: productDtos) {
            ProductResponse productResponse = mapper.map(productDto, ProductResponse.class);
            products.add(productResponse);
        }
        return products;
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody() ProductRequest productRequest) {
        log.info(productRequest.getName());
        ProductDto productDto = service.createProduct(productRequest);
        ProductResponse createdProduct = mapper.map(productDto, ProductResponse.class);
        return createdProduct;
    }

    @GetMapping("reporte")
    public void generateReport() {

    }
}
