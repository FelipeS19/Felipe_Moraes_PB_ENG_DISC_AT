package com.system.controller;

import com.system.service.ProductService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductControllerTest {

    private ProductService service = Mockito.mock(ProductService.class);
    private ProductController controller = new ProductController(service);

    @Test
    void shouldReturnProductsPage() {
        Model model = Mockito.mock(Model.class);

        Mockito.when(service.list()).thenReturn(List.of());

        String view = controller.list(model);

        assertEquals("products", view);
        Mockito.verify(service).list();
    }

    @Test
    void shouldCreateProduct() {
        String view = controller.create(new com.system.model.Product());

        assertEquals("redirect:/products", view);
    }

    @Test
    void shouldDeleteProduct() {
        String view = controller.delete(1L);

        assertEquals("redirect:/products", view);
        Mockito.verify(service).delete(1L);
    }
}