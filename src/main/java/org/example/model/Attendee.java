package org.example.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class Attendee {
    private List<Person> succeeded = new ArrayList<>();
    private List <Person> attended = new ArrayList<>();
    private List <Person> registeredPersons = new ArrayList<>();

    public List<Person> getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(List<Person> succeeded) {
        this.succeeded = succeeded;
    }

    public List<Person> getAttended() {
        return attended;
    }

    public void setAttended(List<Person> attended) {
        this.attended = attended;
    }

    public List<Person> getRegisteredPersons() {
        return registeredPersons;
    }

    public void setRegisteredPersons(List<Person> registeredPersons) {
        this.registeredPersons = registeredPersons;
    }
}
