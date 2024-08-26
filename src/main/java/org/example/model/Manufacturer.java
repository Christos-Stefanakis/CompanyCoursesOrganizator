package org.example.model;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
