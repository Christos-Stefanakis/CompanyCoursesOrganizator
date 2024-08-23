package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.service.CompanyService;
import org.example.service.PersonService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter @Setter
public class Company {

    private int id;
    private String name;
    private Address address;
    private List <Person> employeesList;

    public Company() {

    }

    public Company(int id, String name, Address address) {
        this.name = name;
        this.id = id;
        this.address = address;
        if(this.employeesList==null){
            this.employeesList = new ArrayList<>();
        }else{

        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s, address", this.name, this.address);
    }
}
