package com.example.productservice.repository;

import com.example.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  boolean existsById(Long id);
}
