package com.system.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.system.model.Product;

public class ProductServiceTest {

    @Test
    void shouldCreateProduct(){

        Product product = new Product();

        product.setName("Teste");

        product.setPrice(10);

        product.setQuantity(2);

        assertEquals("Teste", product.getName());

    }

}