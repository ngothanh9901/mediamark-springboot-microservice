package com.example.productservice.service.impl;

import com.example.productservice.dto.parameter.ProductParameter;
import com.example.productservice.dto.request.GetProductByIdsRequest;
import com.example.productservice.dto.request.GetProductRequest;
import com.example.productservice.dto.response.ProductResponse;
import com.example.productservice.dto.response.ResponseObject;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
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
          .map(c -> categoryRepository.findById(c).get())
          .collect(Collectors.toList());
    }

//    Create product
    Product product = productMapper.toProduct(parameter);
    product.setCategories(categories);

//    Save product into database
    productRepository.save(product);

    return product;
  }

  @Override
  public List<Product> getProductByIds(GetProductByIdsRequest request) {
    return productRepository.findAllById(request.getProductIds());
  }

  @Override
  public ResponseObject<ProductResponse> getProduct(GetProductRequest request) {
    Page<Product> products = productRepository.findProduct(request.getText(),request.getPageable());
    List<ProductResponse> content = products.stream().map(x->productMapper.toProductResponse(x)).collect(Collectors.toList());

    ResponseObject<ProductResponse> result = new ResponseObject<>(content,products.getNumber()+1,products.getSize(),
        products.getTotalElements(),products.getTotalPages(),products.isLast());

    return result;
  }

  @Override
  public Product getProductById(Long productId) {
    return productRepository.findById(productId).get();
  }
}
