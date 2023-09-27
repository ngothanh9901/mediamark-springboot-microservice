package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemCartDto {
  private Long productId;
  private String name;
  private Long price;
  private String shortDes;
  private String linkImg;

  private Long productCartId;
  private Long quantity;

  public ProductItemCartDto(ProductDto productDto) {
    this.productId = productDto.getId();
    this.name = productDto.getName();
    this.price = productDto.getPrice();
    this.shortDes = productDto.getShortDes();
    this.linkImg = productDto.getLinkImg();
  }
}
