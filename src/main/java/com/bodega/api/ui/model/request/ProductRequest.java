package com.bodega.api.ui.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductRequest {
    private UUID id;
    private String name;
    private int quantity;
    private int unitPrice;
    private String image;
}
