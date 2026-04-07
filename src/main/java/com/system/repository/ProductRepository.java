package com.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.system.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}