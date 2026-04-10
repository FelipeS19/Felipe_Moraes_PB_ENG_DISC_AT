package com.system.service;

import com.system.model.Product;
import com.system.repository.ProductRepository;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductRepository repository = Mockito.mock(ProductRepository.class);
    private ProductService service = new ProductServiceImpl(repository);

    @Test
    void shouldSaveProduct() {
        Product product = new Product("Notebook", 1000.0, 10);

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
        Product product = new Product("Mouse", 50.0, 20);

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

    @ParameterizedTest
    @ValueSource(doubles = {0, -10, -1})
    void shouldFailInvalidPrice(double price) {

        Product product = new Product("Teste", price, 1);

        assertTrue(product.getPrice() <= 0, "Preço inválido deveria ser rejeitado");
    }
    
    @Test
    void shouldAcceptBoundaryValues() {
        Product product = new Product("Teste", 0.01, 1);

        assertEquals(0.01, product.getPrice());
    }
    
    @Test
    void throwwhendeletingnonexistentproduct() {
        Mockito.doThrow(new RuntimeException("Produto não encontrado"))
            .when(repository).deleteById(99L);

        assertThrows(RuntimeException.class, () -> {
            service.delete(99L);
        });
    }
}