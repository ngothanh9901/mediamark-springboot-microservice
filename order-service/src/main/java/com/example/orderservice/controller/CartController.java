package com.example.orderservice.controller;

import com.example.orderservice.dto.parameter.AddToCartParameter;
import com.example.orderservice.dto.response.CartResponse;
import com.example.orderservice.dto.resquest.AddToCartRequest;
import com.example.orderservice.dto.resquest.GetCartRequest;
import com.example.orderservice.service.CartService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {
  private final CartService cartService;

  @PostMapping("/")
  public CartResponse addToCart(@RequestBody AddToCartParameter parameter, @AuthenticationPrincipal Jwt jwt) {
    AddToCartRequest request = new AddToCartRequest(parameter.getProductId(), (Long) jwt.getClaims().get("id"));
    return cartService.addToCart(request);
  }
  @GetMapping("/")
  public CartResponse getCart(@AuthenticationPrincipal Jwt jwt){
    GetCartRequest request = new GetCartRequest((Long) jwt.getClaims().get("id"));
    return cartService.getCart(request);
  }
}
