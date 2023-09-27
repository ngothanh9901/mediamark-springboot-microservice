package com.example.orderservice.controller;

import com.example.orderservice.dto.parameter.AddToCartParameter;
import com.example.orderservice.dto.response.CartResponse;
import com.example.orderservice.dto.resquest.AddToCartRequest;
import com.example.orderservice.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {
  private final CartService cartService;

  @PostMapping("/")
  public CartResponse addToCart(@RequestBody AddToCartParameter parameter, @AuthenticationPrincipal Jwt jwt) {
    AddToCartRequest request = new AddToCartRequest(parameter.getProductId(), parameter.getQuantity(), (Long) jwt.getClaims().get("id"));
    return cartService.addToCart(request);
  }
}
