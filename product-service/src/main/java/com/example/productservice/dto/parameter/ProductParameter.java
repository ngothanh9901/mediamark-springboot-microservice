package com.example.productservice.dto.parameter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductParameter {

  @NotBlank
  @Size(min = 3, max = 100, message = "The name should be between 3 and 50 characters")
  private String name;

  @NotBlank
  @Size(min = 3, max = 1000, message = "The name should be between 3 and 50 characters")
  private String shortDes;

  @NotBlank
  @Size(min = 3, max = 2000, message = "The name should be between 3 and 50 characters")
  private String description;

  private Long price;

  private String linkImg;

  private Collection<Long> categoryIds;
}
