package com.bodega.api.service;


import com.bodega.api.shared.dto.CategoryDto;
import reactor.core.publisher.Flux;

public interface CategoryService {
  Flux<CategoryDto> getCategories();
}
