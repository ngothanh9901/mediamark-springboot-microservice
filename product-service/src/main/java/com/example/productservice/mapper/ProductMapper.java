package com.example.productservice.mapper;

import com.example.productservice.dto.parameter.ProductParameter;
import com.example.productservice.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
  Product toProduct(ProductParameter parameter);
}
