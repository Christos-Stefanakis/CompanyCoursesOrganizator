package org.example.service;

import org.example.model.Manufacturer;
import org.example.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerService implements IdMaxValue, AutoIdEditor {

    private List<Manufacturer> manufacturerList = new ArrayList<>();

    public List<Manufacturer> getManufacturerList() {
        return manufacturerList;
    }

    public void setManufacturerList(List<Manufacturer> manufacturerList) {
        this.manufacturerList = manufacturerList;
    }

    public void addManufacturer(String name, String address, String requiresTraining){
        this.manufacturerList.add(new Manufacturer(autoIdEditor(),name, address, requiresTraining));
    }

    public <T> void updateManufacturer(T text, String name, String address, String requiresTraining){
        Manufacturer updateManufacturer = findManufacturer(text).stream().findFirst().get();
        updateManufacturer.setName(name);
        updateManufacturer.setAddress(address);
        updateManufacturer.setRequiresTraining(requiresTraining);
    }

    @Override
    public int autoIdEditor() {
        if(this.manufacturerList.isEmpty()){
            return 1;
        }else{
            for (int i = 1; i <= idMaxValue(); i++) {
                int finalI = i;
                if(this.manufacturerList.stream().noneMatch(e->e.getId() == finalI)){
                    return finalI;
                }
            }
        }return idMaxValue() + 1;
    }

    @Override
    public int idMaxValue() {
        return this.manufacturerList.stream()
                .map(Manufacturer::getId)
                .max(Integer::compareTo).get();
    }

    public <T> List<Manufacturer> findManufacturer(T text){
        List<Manufacturer> manufacturers = new ArrayList<>();
        if (text instanceof Number) {
            manufacturers.addAll(this.manufacturerList.stream().filter(p -> p.getId() == ((Number) text).intValue()).toList());
        }
        if(text instanceof String) {
            manufacturers.addAll(this.manufacturerList.stream().filter(p -> p.getName().toLowerCase().contains (((String) text).toLowerCase())).toList());
            manufacturers.addAll(this.manufacturerList.stream().filter(p -> p.getAddress().toLowerCase().contains (((String) text).toLowerCase())).toList());
            manufacturers.addAll(this.manufacturerList.stream().filter(p -> p.getRequiresTraining().toLowerCase().contains (((String) text).toLowerCase())).toList());
        }
        return manufacturers;
    }

    public List<Product> manufacturerProducts(ProductService productService, Manufacturer manufacturer){
        return productService.getProductList().stream().filter(p->p.getManufacturer() == manufacturer).toList();
    }
}
