package org.example.model;

import org.example.service.PersonService;


public class Person extends PersonService{
    private int id;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private Company employers;

    PersonService personService;

    public Person() {
        this.id = 0;
        this.name = "Administrator";
        this.phoneNumber = "";
        this.emailAddress = "";
    }

    public Person(int id,String name, String phoneNumber, String emailAddress) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.employers = employers == null? new Company() : employers;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Company getEmployers() {
        return employers;
    }

    public void setEmployers(Company employers) {
        this.employers = employers;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s, Phone: %s, email: %s, employer: %s"
                , this.id, this.name, this.phoneNumber, this.emailAddress, this.employers);
    }
}
