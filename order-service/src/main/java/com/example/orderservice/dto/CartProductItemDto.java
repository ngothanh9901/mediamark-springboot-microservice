package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProductItemDto {
    private Long productId;
    private String name;
    private Long price;
    private String shortDes;
    private String linkImg;

    private Long quantity;
    private Long CartProductId;
}
