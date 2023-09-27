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
    private Long quantity;
    private Long userId;

    public AddToCartRequest(Long productId, Long quantity, Long userId) {
        this.productId = productId;
        this.quantity = quantity;
        this.userId = userId;
    }
}
