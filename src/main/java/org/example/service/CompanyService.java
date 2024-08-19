package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Company;
import org.example.model.Person;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CompanyService implements IdMaxValue, AutoIdEditor{

    private List<Company> companiesList = new ArrayList<>();
    private List<Company> companies = getCompaniesList();
    private List<Person> persons = getPersons();


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
        if (this.companiesList.stream().noneMatch(c -> c.getName().equals(name))) {
            return true;
        } else return false;
    }

    @Override
    public int idMaxValue() {
        return this.companiesList.stream()
                .map(e -> e.getId())
                .max(Integer::compare).get();
    }

    @Override
    public int autoIdEditor(){
        if(this.companiesList.isEmpty()){
            return 1;
        }else{
            for (int i = 1; i <= idMaxValue(); i++) {
                int finalI = i;
                if(this.companiesList.stream().noneMatch(e->e.getId() == finalI)){
                    return finalI;
                }
            }
        }return idMaxValue() + 1;
    }

    public void employPerson(PersonService personService, Company company, int id){
        List<Person> persons = personService.getPersonList();
        persons.stream().filter(p->p.getId() == id).findAny().get().setEmployers(company);
        company.getEmployeesList().add(personService.getPersonList().stream().filter(p->p.getId() == id).findAny().get());
    }

    public <T> List<Company> findCompany(T text){
        List<Company> companies = new ArrayList<>();
        if(text instanceof Number){
            companies.addAll(this.companiesList.stream().filter(c->c.getId() == ((Number)text).intValue()).toList());
        }
        if(text instanceof String){
            companies.addAll(this.companiesList.stream().filter(c-> c.getName().toLowerCase().toLowerCase().contains(((String) text).toLowerCase())).toList());
            companies.addAll(this.companiesList.stream().filter(c-> c.getAddress().toLowerCase().contains(((String) text).toLowerCase())).toList());
        }
        return  companies;
    }
}
