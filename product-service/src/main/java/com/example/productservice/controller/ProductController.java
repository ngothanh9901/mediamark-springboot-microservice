package com.example.productservice.controller;

import com.example.productservice.dto.parameter.FilterProductParameter;
import com.example.productservice.dto.parameter.ProductParameter;
import com.example.productservice.dto.request.GetProductByIdsRequest;
import com.example.productservice.dto.request.GetProductRequest;
import com.example.productservice.dto.response.ProductResponse;
import com.example.productservice.dto.response.ResponseObject;
import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {
  private final ProductService productService;

  @GetMapping("/")
  public ResponseObject<ProductResponse> getProducts(FilterProductParameter parameter, Pageable pageable) {
    GetProductRequest request = new GetProductRequest(parameter.getText(),parameter.getCategoryIds(),pageable);
    return productService.getProduct(request);
  }
  @GetMapping("/{id}")
  public Product getProductById(@PathVariable("id") Long productId) {
    return productService.getProductById(productId);
  }

  @PostMapping("/")
  public Product save(@Valid @RequestBody ProductParameter parameter) {

    return productService.save(parameter);
  }

  @PostMapping("/ids")
  public List<Product> getProductByIds(@RequestBody GetProductByIdsRequest request) {
    return productService.getProductByIds(request);
  }
}
