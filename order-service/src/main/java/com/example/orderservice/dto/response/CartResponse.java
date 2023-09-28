package com.example.orderservice.dto.response;

import com.example.orderservice.dto.ProductItemCartDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
  private List<ProductItemCartDto> productItems;
  private Long sum;
  private Long idOrder;

}
