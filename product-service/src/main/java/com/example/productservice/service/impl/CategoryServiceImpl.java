package com.example.productservice.service.impl;

import com.example.productservice.model.Category;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
@Getter
@Setter
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;
  @Override
  public Category save(Category category) {
    return categoryRepository.save(category);
  }
}
