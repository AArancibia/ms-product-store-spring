package com.bodega.api.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductResponse {
    public UUID id;
    private String name;
    private int unitPrice;
    private int quantity;
    private String image;
    private UUID categoryId;
    private CategoryResponse category;
}
