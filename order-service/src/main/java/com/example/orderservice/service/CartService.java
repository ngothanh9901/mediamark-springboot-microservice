package com.example.orderservice.service;

import com.example.orderservice.dto.response.CartResponse;
import com.example.orderservice.dto.resquest.AddToCartRequest;
import com.example.orderservice.dto.resquest.GetCartRequest;

public interface CartService {
  CartResponse addToCart(AddToCartRequest request);
  CartResponse getCart(GetCartRequest request);
}
