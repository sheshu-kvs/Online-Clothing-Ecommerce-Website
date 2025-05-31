package com.sheshu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sheshu.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
}
