package com.example.productservice.service.impl;

import com.example.productservice.dto.parameter.ProductParameter;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final CategoryRepository categoryRepository;
  private final ProductMapper productMapper;

  public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
    this.productMapper = Mappers.getMapper(ProductMapper.class);
  }

  @Override
  public Product save(ProductParameter parameter) {
//    Get list category
    List<Category> categories = new ArrayList<>();
    if (!CollectionUtils.isEmpty(parameter.getCategoryIds())) {
      categories = parameter.getCategoryIds().parallelStream()
          .map(c->categoryRepository.findById(c).get())
          .collect(Collectors.toList());
    }

//    Create product
    Product product = productMapper.toProduct(parameter);
    product.setCategories(categories);

//    Save product into database
    productRepository.save(product);

    return product;
  }
}
