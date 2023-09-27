package com.example.orderservice.dto.response;

import com.example.orderservice.dto.ProductItemCartDto;

import java.util.List;

public class CartResponse {
  private List<ProductItemCartDto> productItems;
  private Long sum;
  private Long idOrder;

}
