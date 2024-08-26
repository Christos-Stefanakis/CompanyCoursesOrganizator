package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Manufacturer;
import org.example.model.Person;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ProductService implements  IdMaxValue, AutoIdEditor{

    private List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(String name, double price, Manufacturer manufacturer){
        this.productList.add(new Product(autoIdEditor(), name, price, manufacturer));
    }

    public <T> void updateProduct(T text, String name, double price, Manufacturer manufacturer){
        Product updateProduct = findProduct(text).stream().findFirst().get();
        updateProduct.setName(name);
        updateProduct.setPrice(price);
        updateProduct.setManufacturer(manufacturer);
    }

    @Override
    public int autoIdEditor() {
        if(this.productList.isEmpty()){
            return 1;
        }else{
            for (int i = 1; i <= idMaxValue(); i++) {
                int finalI = i;
                if(this.productList.stream().noneMatch(e->e.getId() == finalI)){
                    return finalI;
                }
            }
        }return idMaxValue() + 1;
    }

    @Override
    public int idMaxValue() {
        return this.productList.stream()
                .map(Product::getId)
                .max(Integer::compareTo).get();
    }

    public <T> List<Product> findProduct(T text){
        List<Product> products = new ArrayList<>();
        if (text instanceof Number) {
             products.addAll(this.productList.stream().filter(p -> p.getId() == ((Number) text).intValue()).toList());
             products.addAll(this.productList.stream().filter(p -> p.getPrice() == ((Number) text).doubleValue()).toList());
        }
        if(text instanceof String) {
            products.addAll(this.productList.stream().filter(p -> p.getName().contains((String)text)).toList());
        }
        return products;
    }
}
