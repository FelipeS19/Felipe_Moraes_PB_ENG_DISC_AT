package com.system.service;

import com.system.model.Product;
import com.system.repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductRepository repository = Mockito.mock(ProductRepository.class);
    private ProductService service = new ProductServiceImpl(repository);

    @Test
    void shouldSaveProduct() {
        Product product = new Product();
        product.setName("Notebook");

        Mockito.when(repository.save(product)).thenReturn(product);

        Product saved = service.save(product);

        assertEquals("Notebook", saved.getName());
    }

    @Test
    void shouldDeleteProduct() {
        service.delete(1L);

        Mockito.verify(repository).deleteById(1L);
    }

    @Test
    void shouldFindProductById() {
        Product product = new Product();
        product.setName("Mouse");

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));

        Product found = service.findById(1L);

        assertEquals("Mouse", found.getName());
    }

    @Test
    void ThrowProductNotFound() {

    Mockito.when(repository.findById(99L))
           .thenReturn(java.util.Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> {
        service.findById(99L);
    });
}
}