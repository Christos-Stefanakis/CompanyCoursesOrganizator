package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Address;
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

    public void addCompany(String name, String country, String cityName, String stateAbbreviation, String streetName, String zipCode, int buildNumber) {
        if (checkCompanyValidName(name)) {
            this.companiesList.add(
                    new Company(autoIdEditor(), name, new Address(country, cityName, stateAbbreviation, streetName, zipCode, buildNumber))
            );
        }
    }

    public <T> void updateCompany(int id, String name, String country, String cityName, String stateAbbreviation, String streetName, String zipCode, int buildNumber){
        Company selectedCompany = selectCompanyById(id);
        selectedCompany.setName(name);
        selectedCompany.setAddress(new Address(country, cityName, stateAbbreviation, streetName, zipCode, buildNumber));
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
        List<Company> companies = new ArrayList<>();
            companies.addAll(findCompaniesByCountry(text));
            companies.addAll(findCompaniesByCityName(text));
            companies.addAll(findCompaniesByStreetName(text));
            companies.addAll(findCompaniesByStateAbbreviation(text));
            companies.addAll(findCompaniesByBuildNumber(Integer.parseInt(text)));
            companies.addAll(findCompaniesByZipCode(text));
        return companies;
    }

    public List<Company> findCompaniesByCountry(String country){
        return this.companiesList
                .stream()
                .filter(c->c.getAddress().getCountry().toLowerCase().contains(country.toLowerCase()))
                .toList();
    }

    public List<Company> findCompaniesByCityName(String cityName){
        return this.companiesList
                .stream()
                .filter(c->c.getAddress().getCityName().toLowerCase().contains(cityName.toLowerCase()))
                .toList();
    }

    public List<Company> findCompaniesByStreetName(String streetName){
        return this.companiesList
                .stream()
                .filter(c->c.getAddress().getStreetName().toLowerCase().contains(streetName.toLowerCase()))
                .toList();
    }

    public List<Company> findCompaniesByStateAbbreviation(String stateAbbreviation){
        return this.companiesList
                .stream()
                .filter(c->c.getAddress().getStateAbbreviation().toLowerCase().contains(stateAbbreviation.toLowerCase()))
                .toList();
    }

    public List<Company> findCompaniesByBuildNumber(int buildNumber){
        return this.companiesList
                .stream()
                .filter(c->c.getAddress().getBuildNumber() == buildNumber)
                .toList();
    }

    public List<Company> findCompaniesByZipCode(String zipCode){
        return this.companiesList
                .stream()
                .filter(c->c.getAddress()
                        .getZipCode()
                        .replaceAll(" ", "")
                        .contains(zipCode.replaceAll(" ","")))
                .toList();
    }

    public List <Person> companyEmployees(Company company){
        return company.getEmployeesList();
    }

    public void removeCompany(int id){
       this.companiesList = this.companiesList.stream()
               .filter(c->c != selectCompanyById(id))
               .toList();
       for(Person person: personService.personsWithSpecificEmployee(id)){
           person.setEmployers(new Company());
       }
    }
}
