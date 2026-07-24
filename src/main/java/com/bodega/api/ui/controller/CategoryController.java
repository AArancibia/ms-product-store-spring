package com.bodega.api.ui.controller;

import com.bodega.api.service.CategoryService;
import com.bodega.api.ui.model.response.CategoryResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "Role", description = "Endpoint methods for role feature")
@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final ModelMapper mapper;
  
  private final CategoryService service;

  @Operation(summary = "List of product categories")
  @ApiResponses(value = {
  		@ApiResponse(responseCode = "200", description = "Get all categories"),
  })
  @GetMapping
  public Flux<CategoryResponse> getCategories() {
    return service.getCategories()
      .map(categoryDto -> mapper.map(categoryDto, CategoryResponse.class));
  }
}
