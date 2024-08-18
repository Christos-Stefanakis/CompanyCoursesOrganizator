package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Manufacturer {
    private String name;
    private int id;
    private String address;
    private String requiresTraining;

    List<Manufacturer> manufacturerList = new ArrayList<>();

    public Manufacturer() {
    }

    public Manufacturer(String name, int id, String address, String requiresTraining) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.requiresTraining = requiresTraining;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRequiresTraining() {
        return requiresTraining;
    }

    public void setRequiresTraining(String requiresTraining) {
        this.requiresTraining = requiresTraining;
    }

    public List<Manufacturer> getManufacturerList() {
        return manufacturerList;
    }

    public void setManufacturerList(List<Manufacturer> manufacturerList) {
        this.manufacturerList = manufacturerList;
    }

    public int idMaxValue() {
        return this.manufacturerList.stream()
                .map(e -> e.id)
                .max(Integer::compare).get();
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", address='" + address + '\'' +
                ", requiresTraining='" + requiresTraining + '\'' +
                '}';
    }
}
