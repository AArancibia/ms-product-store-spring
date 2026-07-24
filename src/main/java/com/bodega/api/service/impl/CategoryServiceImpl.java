package com.bodega.api.service.impl;

import com.bodega.api.repository.CategoryRepository;
import com.bodega.api.service.CategoryService;
import com.bodega.api.shared.dto.CategoryDto;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

  private final ModelMapper mapper;
  
  private final CategoryRepository repository;

  @Transactional(readOnly = true)
  @Override
  public Flux<CategoryDto> getCategories() {
    return Flux.fromIterable(repository.findAll())
      .map(category -> mapper.map(category, CategoryDto.class));
  }
}
