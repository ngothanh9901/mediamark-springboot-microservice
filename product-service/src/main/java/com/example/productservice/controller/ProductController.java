package com.example.productservice.controller;

import com.example.productservice.dto.parameter.ProductParameter;
import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {
  private final ProductService productService;
  @PostMapping("/")
  public Product save(@Valid @RequestBody ProductParameter parameter){
    return productService.save(parameter);
  }
}
