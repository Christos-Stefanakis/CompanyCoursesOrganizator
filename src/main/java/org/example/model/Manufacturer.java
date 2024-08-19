package org.example.model;

import lombok.Getter;

@Getter
public class Manufacturer {
    private int id;
    private String name;
    private String address;
    private String requiresTraining;


    public Manufacturer() {
    }

    public Manufacturer(int id, String name, String address, String requiresTraining) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.requiresTraining = requiresTraining;
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
