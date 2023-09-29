package com.example.orderservice.cache;

import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.feign.ProductClient;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

@Component
@EnableCaching
public class ProductCache {
  private final CacheManager cacheManager;
  private final ProductClient productClient;

  public ProductCache(CacheManager cacheManager, ProductClient productClient) {
    this.cacheManager = cacheManager;
    this.productClient = productClient;
  }

  public void evictCache(String cacheName) {
    cacheManager.getCache(cacheName).clear();
  }

  @Cacheable("product")
  public ProductDto getProductById(Long productId) {
    return productClient.getProductById(productId);
  }
}
