package com.example.productservice.service;

import com.example.productservice.dto.parameter.ProductParameter;
import com.example.productservice.model.Product;

public interface ProductService {
  Product save(ProductParameter parameter);
}
