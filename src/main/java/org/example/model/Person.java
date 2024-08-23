package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.service.PersonService;


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
        this.employers = employers == null? new Company(0, "Úřad práce", new Address("Czechia", "Prague", "Prague 7", "Holešovice", "170 00", 25)) : employers;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s, Phone: %s, email: %s, employer: %s"
                , this.id, this.name, this.phoneNumber, this.emailAddress, this.employers);
    }
}
