package org.example.model;


import org.example.service.CompanyService;
import org.example.service.PersonService;
import java.util.ArrayList;
import java.util.List;


public class Company {
    private int id;
    private String name;
    private Address address;
    private List <Person> employeesList;

    PersonService personService;
    CompanyService companyService;

    public Company() {
        this.address = new Address();
        if(this.employeesList==null){
            this.employeesList = new ArrayList<>();
        }else{
            this.employeesList.add(new Person());
        }
    }

    public Company(int id, String name, Address address) {
        this.name = name;
        this.id = id;
        this.address = address;
        if(this.employeesList==null){
            this.employeesList = new ArrayList<>();
        }else{
            this.employeesList.add(new Person());
        }
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Person> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Person> employeesList) {
        this.employeesList = employeesList;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public String toString() {
        if(this.id == 0){
            return "unemployed";
        }
        return String.format("[%s] %s, address", this.id, this.name, this.address);
    }
}
