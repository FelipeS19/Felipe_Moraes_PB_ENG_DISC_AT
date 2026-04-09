package com.system.service;

import java.util.List;
import com.system.model.Product;

public interface ProductService {

    Product save(Product product);
    
    List<Product> list();

    void delete(Long id);
    
    Product findById(Long id);
}