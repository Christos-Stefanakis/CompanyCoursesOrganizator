package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Company {

    private String name;
    private int id;
    private String address;
    private List<Company> companiesList = new ArrayList<>();

    public Company() {
    }

    public Company(String name, String address) {
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

    public void addCompany(String name, String address){
        String companyName="";
        if (checkCompanyValidName(name)){companyName = name;}

    }
     public boolean checkCompanyValidName(String name){
        if(this.companiesList.stream().noneMatch(c->c.name.equals(name))){
            return true;
        }else return false;
     }

     public int idMaxValue(){
        return this.companiesList.stream()
                .max(Comparator.comparingInt(a -> a.id)).map(id->id.id).get();
     }

     public int autoIdEditor(){
        return 0;
     }
}
