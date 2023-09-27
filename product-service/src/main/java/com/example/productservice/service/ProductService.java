package com.example.productservice.service;

import com.example.productservice.dto.parameter.ProductParameter;
import com.example.productservice.dto.request.GetProductByIdsRequest;
import com.example.productservice.model.Product;

import java.util.List;

public interface ProductService {
  Product save(ProductParameter parameter);
  List<Product> getProductByIds(GetProductByIdsRequest request);
}
