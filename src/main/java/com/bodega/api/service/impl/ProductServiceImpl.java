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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    ProductRepository repository;

    @Override
    public List<ProductDto> getProducts() {
        ArrayList<ProductDto> products = new ArrayList<>();
        List<ProductEntity> productEntities = (List<ProductEntity>) repository.findAll();
        for (ProductEntity entity: productEntities) {
            ProductDto productDto = mapper.map(entity, ProductDto.class);
            products.add(productDto);
        }
        return products;
    }

    @Override
    public ProductDto createProduct(ProductRequest createProductRequest) {

        ProductEntity product = mapper.map(createProductRequest, ProductEntity.class);

        ProductEntity createdProduct = repository.save(product);

        ProductDto productDto = mapper.map(createdProduct, ProductDto.class);

        return productDto;
    }

}
