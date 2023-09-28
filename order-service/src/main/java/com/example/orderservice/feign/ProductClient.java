package com.example.orderservice.feign;

import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.dto.resquest.GetProductByIdsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value ="product-service")
public interface ProductClient {
  @PostMapping("/api/product/ids")
  List<ProductDto> getProductByIds(@RequestBody GetProductByIdsRequest request);
  @GetMapping("/api/product/{id}")
  ProductDto getProductById(@PathVariable("id") Long productId);
}
