package com.bodega.api.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryResponse {
    private UUID id;
    private String name;
}
