package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private String name;
    private int id;
    private String address;
    private List<Company> companiesList = new ArrayList<>();

    public Company() {
    }

    public Company(int id, String name, String address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    public Company(List<Company> companiesList) {
        this.companiesList = companiesList;
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

    public List<Company> getCompaniesList() {
        return companiesList;
    }

    public void setCompaniesList(List<Company> companiesList) {
        this.companiesList = companiesList;
    }

    public void addCompany(String name, String address) {
        String companyName = "";
        if (checkCompanyValidName(name)) {
            companyName = name;
            this.companiesList.add(
                    new Company(autoIdEditor(), name, address)
            );
        }

    }

    public boolean checkCompanyValidName(String name) {
        if (this.companiesList.stream().noneMatch(c -> c.name.equals(name))) {
            return true;
        } else return false;
    }


    public int idMaxValue() {
        return this.companiesList.stream()
                .map(e -> e.id)
                .max(Integer::compare).get();
    }


    public int autoIdEditor(){
        if(this.companiesList.isEmpty()){
            return 1;
        }else{
            for (int i = 1; i <= idMaxValue(); i++) {
                int finalI = i;
                if(this.companiesList.stream().noneMatch(e->e.id == finalI)){
                    return finalI;
                }
            }
        }return idMaxValue() + 1;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}

