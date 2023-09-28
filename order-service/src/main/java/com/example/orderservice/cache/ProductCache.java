package com.example.orderservice.cache;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class ProductCache {
  private final CacheManager cacheManager;

  public ProductCache(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  public void evictCache(String cacheName) {
    cacheManager.getCache(cacheName).clear();
  }

}
