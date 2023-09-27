package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
  private Long id;

  private String name;

  private String shortDes;

  private String description;

  private Long price;

  private String linkImg;
}
