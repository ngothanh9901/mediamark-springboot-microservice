package com.example.orderservice.service;

import com.example.orderservice.dto.response.CartResponse;
import com.example.orderservice.dto.resquest.AddToCartRequest;

public interface CartService {
  CartResponse addToCart(AddToCartRequest request);
}
