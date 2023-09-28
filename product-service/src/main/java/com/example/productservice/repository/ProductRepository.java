package com.example.productservice.repository;

import com.example.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
  @Query("SELECT p FROM Product p WHERE :name is null OR p.name LIKE  CONCAT('%',:name,'%')")
  Page<Product> findProduct(@Param("name") String name, Pageable pageable);
}
