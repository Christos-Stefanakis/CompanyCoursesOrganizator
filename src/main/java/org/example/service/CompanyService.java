package org.example.service;

import org.example.model.Address;
import org.example.model.Company;
import org.example.model.Person;
import org.example.model.Training;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompanyService implements IdMaxValue, AutoIdEditor{

    private List <Company> companiesList = new ArrayList<>();

    public CompanyService() {
    }

    public CompanyService(List<Company> companiesList) {
        this.companiesList = companiesList;
    }

    public List<Company> getCompaniesList() {
        return companiesList;
    }

    public void setCompaniesList(List<Company> companiesList) {
        this.companiesList = companiesList;
    }

    private PersonService personService;
    private Company company;
    private Person person;
    private TrainingService trainingService;


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

    public <T> List<Company> findCompaniesByAddress(T text){
        List<Company> companies = new ArrayList<>();
        if(text instanceof String) {
            companies.addAll(findCompaniesByCountry((String) text));
            companies.addAll(findCompaniesByCityName((String) text));
            companies.addAll(findCompaniesByStreetName((String) text));
            companies.addAll(findCompaniesByStateAbbreviation((String) text));
            companies.addAll(findCompaniesByZipCode((String) text));
        }
        if(text instanceof Number) {
            companies.addAll(findCompaniesByBuildNumber(((Number) text).intValue()));
        }
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

    public List<Company> findCompaniesByBuildNumber(int text){
        return this.companiesList
                .stream()
                .filter(c->c.getAddress().getBuildNumber() == text)
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

    public void removeCompany(Company company){
       this.companiesList = this.companiesList.stream()
               .filter(c->c != company)
               .toList();
    }

    public List <Company> findCompanyByEmployee(Person person){
        return this.companiesList.stream().filter(c->c.getEmployeesList() == person).toList();
    }

    public void signUpPerson(Training training, Person person){
        if (this.trainingService.getSignUpPersons().containsKey(training.getId())){
            this.trainingService.getSignUpPersons().get(training.getId()).add(person);
        }else{
            List<Person> signPersons = new ArrayList<>();
            signPersons.add(person);
            this.trainingService.getSignUpPersons().put(training.getId(), signPersons);
        }
    }
}
