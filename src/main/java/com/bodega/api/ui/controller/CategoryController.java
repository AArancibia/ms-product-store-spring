package com.bodega.api.ui.controller;

import com.bodega.api.service.CategoryService;
import com.bodega.api.ui.model.response.CategoryResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/categories")
public class CategoryController {
  @Autowired
  private ModelMapper mapper;
  @Autowired
  private CategoryService service;

  @GetMapping
  public Flux<CategoryResponse> getCategories() {
    return service.getCategories()
      .map(categoryDto -> mapper.map(categoryDto, CategoryResponse.class));
  }
}
