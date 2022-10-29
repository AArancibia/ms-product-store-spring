package com.bodega.api.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductDto {
    public UUID id;
    private String name;
    private int unitPrice;
    private int quantity;
    private String image;
    private UUID categoryId;
    private CategoryDto category;
}
