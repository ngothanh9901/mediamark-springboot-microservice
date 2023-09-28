package com.example.orderservice.dto.resquest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddToCartRequest {
  private Long productId;
  private Long userId;

  public AddToCartRequest(Long productId, Long userId) {
    this.productId = productId;
    this.userId = userId;
  }
}
