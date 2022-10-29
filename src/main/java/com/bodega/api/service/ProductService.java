package com.bodega.api.service;

import com.bodega.api.shared.dto.ProductDto;
import com.bodega.api.ui.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProducts();
    ProductDto createProduct(ProductRequest createProductRequest);
}
