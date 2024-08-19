package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.service.PersonService;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Person extends PersonService{
    private int id;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private Company employers;

    PersonService personService;

    public Person() {

    }

    public Person(int id,String name, String phoneNumber, String emailAddress) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.employers = employers == null? new Company(0, "Úřad práce", "Ústí nad Labem") : employers;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", employers=" + employers +
                '}';
    }
}
