package com.bodega.api.service.impl;

import com.bodega.api.repository.CategoryRepository;
import com.bodega.api.service.CategoryService;
import com.bodega.api.shared.dto.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private ModelMapper mapper;
  @Autowired
  private CategoryRepository repository;

  @Override
  public Flux<CategoryDto> getCategories() {
    return Flux.fromIterable(repository.findAll())
      .map(category -> mapper.map(category, CategoryDto.class));
  }
}
