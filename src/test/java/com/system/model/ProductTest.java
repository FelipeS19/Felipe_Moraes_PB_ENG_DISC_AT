package com.system.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void shouldSetAndGetFields() {
        Product p = new Product("Teclado", 100, 2);

        assertEquals("Teclado", p.getName());
        assertEquals(100, p.getPrice());
        assertEquals(2, p.getQuantity());
    }
}