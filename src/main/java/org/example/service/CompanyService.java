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

    private PersonService personService;
    private Company company;
    private Person person;

    public void addCompany(String name, String address) {
        if (checkCompanyValidName(name)) {
            this.companiesList.add(
                    new Company(autoIdEditor(), name, address)
            );
        }

    }

    public <T> void updateCompany(T text, String name, String address){
        Company updateCompany = findCompany(text).stream().findFirst().get();
        updateCompany.setName(name);
        updateCompany.setAddress(address);
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

    public void employPerson(Company company, Person person){
        company.getEmployeesList().add(person);
        person.setEmployers(company);
    }

    public <T> List<Company> findCompany(T text){
        List<Company> companies = new ArrayList<>();
        if(text instanceof Number){
            companies.add(selectCompanyById(((Number)text).intValue()));
        }
        if(text instanceof String){
            companies.addAll(findCompaniesByName((String) text));
            companies.addAll(findCompaniesByAddress((String) text));
        }
        return  companies;
    }


    public Company selectCompanyById(int id){
        return companiesList
                .stream()
                .filter(c->c.getId() == id)
                .findFirst()
                .get();
    }

    public List<Company> findCompaniesByName(String text){
        return this.companiesList
                .stream()
                .filter(c-> c.getName()
                        .toLowerCase()
                        .contains(text
                                .toLowerCase())).toList();
    }

    public List<Company> findCompaniesByAddress(String text){
        return this.companiesList
                .stream()
                .filter(c-> c.getAddress()
                        .toLowerCase()
                        .contains(text.toLowerCase())).toList();
    }

    public List <Person> companyEmployees(Company company){
        return company.getEmployeesList();
    }
}
