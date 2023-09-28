package com.example.productservice.service;

import com.example.productservice.dto.parameter.ProductParameter;
import com.example.productservice.dto.request.GetProductByIdsRequest;
import com.example.productservice.dto.request.GetProductRequest;
import com.example.productservice.dto.response.ProductResponse;
import com.example.productservice.dto.response.ResponseObject;
import com.example.productservice.model.Product;

import java.util.List;

public interface ProductService {
  Product save(ProductParameter parameter);
  List<Product> getProductByIds(GetProductByIdsRequest request);
  ResponseObject<ProductResponse> getProduct(GetProductRequest request);
  Product getProductById(Long productId);
}
