package org.example.service;

import org.example.model.Manufacturer;
import org.example.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturerServiceTest {

    ManufacturerService manufacturerService = new ManufacturerService();
    ProductService productService = new ProductService();

    @Test
    void addManufacturer() {
        manufacturerService.addManufacturer("Coursera","onLine", "");
        manufacturerService.addManufacturer("AWS Training","onLine", "");

        Manufacturer coursera = manufacturerService.getManufacturerList().stream().filter(m->m.getName().equals("Coursera")).findAny().get();
        Manufacturer aws = manufacturerService.getManufacturerList().stream().filter(m->m.getId() == 2).findAny().get();

        assertEquals(coursera, manufacturerService.findManufacturer("coursera").stream().findAny().get());
        assertEquals(coursera, manufacturerService.findManufacturer(1).stream().findAny().get());
        assertEquals(aws, manufacturerService.findManufacturer("aws").stream().findAny().get());
        assertEquals(aws, manufacturerService.findManufacturer(2).stream().findAny().get());
    }

    @Test
    void updateManufacturer() {
        manufacturerService.addManufacturer("Coursera","onLine", "");
        manufacturerService.addManufacturer("AWS Training","onLine", "");

        Manufacturer coursera = manufacturerService.getManufacturerList().stream().filter(m->m.getName().equals("Coursera")).findAny().get();
        manufacturerService.updateManufacturer("Coursera", "SDA", "Prague", "Java");
        assertEquals("SDA", coursera.getName());
        assertEquals("Prague", coursera.getAddress());
        assertEquals("Java", coursera.getRequiresTraining());
    }

    @Test
    void manufacturerProducts() {
        Manufacturer sda = new Manufacturer(1, "SDA", "Praha", "Java");
        Manufacturer pyth = new Manufacturer(2, "Python Academy", "Brno", "Python");

        Product javaB = new Product (1,"Java Basic", 60000, sda);
        Product javaA = new Product (2,"Java Advance", 70000, sda);
        Product pythonB = new Product (3,"Python Basic", 70000, pyth);
        Product pythonA = new Product (4,"Python Advance", 80000, pyth);

        productService.getProductList().add(javaA);
        productService.getProductList().add(javaB);
        productService.getProductList().add(pythonA);
        productService.getProductList().add(pythonB);


        List<Product> sdaProducts = List.of(javaA, javaB);
        List<Product> pythProducts = List.of(pythonA, pythonB);

       assertEquals(sdaProducts, manufacturerService.manufacturerProducts(productService, sda));
       assertEquals(pythProducts, manufacturerService.manufacturerProducts(productService, pyth));

    }
}