package com.example.productservice.controller;

import com.example.productservice.model.Category;
import com.example.productservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping("/")
  public Category save(@Valid @RequestBody Category category) {
    return categoryService.save(category);
  }
}
