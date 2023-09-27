package com.example.orderservice.feign;

import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.dto.resquest.GetProductByIdsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value ="product-service")
public interface ProductClient {
  @PostMapping("/api/product/ids")
  List<ProductDto> getProductByIds(@RequestBody GetProductByIdsRequest request);
}
