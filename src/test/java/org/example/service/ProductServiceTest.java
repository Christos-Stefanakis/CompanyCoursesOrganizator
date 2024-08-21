package org.example.service;

import org.example.model.Manufacturer;
import org.example.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    ProductService productService = new ProductService();
    @Test
    void addProduct() {
        Manufacturer sda = new Manufacturer(1, "SDA", "Praha", "Java");
        productService.addProduct("Java", 60000, sda);
        assertEquals(1, productService.getProductList().size());
    }

    @Test
    void findProduct() {
        Manufacturer sda = new Manufacturer(1, "SDA", "Praha", "Java");
        Manufacturer pyth = new Manufacturer(2, "Python Academy", "Brno", "Python");
        Manufacturer sqlac = new Manufacturer(3, "SQL Academy", "Brno", "SQL");
        Manufacturer javaAcademy = new Manufacturer(4, "Java Academy", "Brno", "Java");

        productService.addProduct("Java", 60000,sda);
        productService.addProduct("Python", 70000,pyth);
        productService.addProduct("SQL", 70000,sqlac);

        Product java = productService.getProductList().stream().filter(p->p.getName().equals("Java")).findAny().get();

        assertEquals(java, productService.findProduct("Java").stream().findAny().get());
    }

    @Test
    void updateProduct() {
        Manufacturer sda = new Manufacturer(1, "SDA", "Praha", "Java");
        Manufacturer pyth = new Manufacturer(2, "Python Academy", "Brno", "Python");
        Manufacturer sqlac = new Manufacturer(3, "SQL Academy", "Brno", "SQL");
        Manufacturer javaAcademy = new Manufacturer(4, "Java Academy", "Brno", "Java");

        productService.addProduct("Java", 60000, sda);
        productService.addProduct("Python", 70000, pyth);
        productService.addProduct("SQL", 70000, sqlac);

        Product java = productService.getProductList().stream().filter(p->p.getName().equals("Java")).findAny().get();
        productService.updateProduct("Java", "Java Basics", 45000, javaAcademy);
        assertEquals("Java Basics", java.getName());
        assertEquals(45000, java.getPrice());
        assertEquals("Java Academy", java.getManufacturer().getName());
        assertEquals(javaAcademy, java.getManufacturer());
    }
}