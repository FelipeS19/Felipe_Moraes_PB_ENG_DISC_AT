package com.system.controller;

import com.system.service.ProductService;
import org.springframework.validation.BindingResult;

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

        ProductForm form = new ProductForm();
        form.setName("Notebook");
        form.setPrice(1000.0);
        form.setQuantity(10);

        BindingResult result = Mockito.mock(BindingResult.class);
        Model model = Mockito.mock(Model.class);

        Mockito.when(result.hasErrors()).thenReturn(false);

        String view = controller.create(form, result, model);

        assertEquals("redirect:/products", view);
    }

    @Test
    void shouldDeleteProduct() {
        String view = controller.delete(1L);

        assertEquals("redirect:/products", view);
        Mockito.verify(service).delete(1L);
    }
}