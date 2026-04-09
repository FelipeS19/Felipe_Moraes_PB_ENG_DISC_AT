package com.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.system.model.Product;
import com.system.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }
    public Product findById(Long id){
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> list() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}