package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private int id;
    private String name;
    private double price;
    private Manufacturer manufacturer;

    public Product() {
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer == null? new Manufacturer() : manufacturer;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
