package org.example.service;

import org.example.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    ProductService productService = new ProductService();
    @Test
    void addProduct() {
        productService.addProduct("Java", 60000);
        assertEquals(1, productService.getProductList().size());
    }

    @Test
    void findProduct() {
        productService.addProduct("Java", 60000);
        productService.addProduct("Python", 70000);
        productService.addProduct("SQL", 70000);

        Product java = productService.getProductList().stream().filter(p->p.getName().equals("Java")).findAny().get();

        assertEquals(java, productService.findProduct("Java").stream().findAny().get());
    }
}