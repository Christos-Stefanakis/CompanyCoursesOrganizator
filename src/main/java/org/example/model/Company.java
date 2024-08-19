package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.service.CompanyService;
import org.example.service.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter @Setter
public class Company extends CompanyService {

    private int id;
    private String name;
    private String address;
    private List <Person> employeesList;

    public Company() {

    }

    public Company(int id, String name, String address) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.employeesList = getEmployeesList() == null? new ArrayList<>() : getEmployeesList();
    }


    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", employeesList=" + employeesList.size() +
                '}';
    }
}
