package org.example;

import org.example.model.Person;
import org.example.service.PersonService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person person = new Person();
    PersonService personService = new PersonService();

    @Test
    void nameValedFlase() {
        String[] validNames = {"John Doe","Alice Smith","Jean-Luc Picard"};
        String[] invalidNames = {"John", "john doe", "John-Doe", "John  Doe"};
        for(String n: invalidNames){
            assertFalse(personService.nameValid(n));
        }
        for(String n: validNames){
            assertTrue(personService.nameValid(n));
        }

    }

    @Test
    void phoneValid() {
        String[] validPhoneNumbers = {"+420 000 000 000", "+420000000000", "+300 000000000"};
        String[] invalidPhoneNumbers = {"000 000 000", "658 657 1"};
        for(String n: validPhoneNumbers){
            assertTrue(personService.phoneValid(n));
        }
        for (String n: invalidPhoneNumbers){
            assertFalse(personService.phoneValid(n));
        }
    }

    @Test
    void emailValidator() {
        String[] validMails = {"jan@gmail.com", "jan.nov@seznam.cz"};
        String[] invalidMails = {"jan.gmail.com", "jan@com"};
        for(String m: validMails){
            assertTrue(personService.emailValidator(m));
        }
        for(String m:invalidMails){
            assertFalse(personService.emailValidator(m));
        }
    }

    @Test
    void addPerson() {
        personService.addPerson("Jana Novakova", "+420 000 000 001", "jana@ip.gr");
        personService.addPerson("Jana Stara", "+420 000 000 00", "jana@outlook.gr");
        personService.addPerson("Jana Chuda", "+420 000 000 002", "janain.gr");
        personService.addPerson("Jana Novakova", "+420 000 000 003", "jana@seznam.gr");
        personService.addPerson("Jana Strasna", "+420 000 000 004", "jana@in.gr");
        personService.addPerson("Jana Strasna", "+420 000 000 001", "jana@google.gr");
        assertEquals(2, personService.getPersonList().size());
    }

    @Test
    void checkDuplicatePhone() {
        personService.addPerson("Jana Novakova", "+420 000 000 000", "jana@in.gr");
        assertTrue(personService.checkDuplicatePhone("+420000000000"));
        assertTrue(personService.checkDuplicatePhone("+420 000 000 000"));
        assertFalse(personService.checkDuplicatePhone("+420000000001"));
    }

    @Test
    void checkDuplicateEmail() {
        personService.addPerson("Jana Novakova", "+420 000 000 000", "jana@in.gr");
        assertTrue(personService.checkDuplicateEmail("jana@in.gr"));
        assertFalse(personService.checkDuplicateEmail("jana@gmail.gr"));
    }

    @Test
    void checkDuplicateName() {
        personService.addPerson("Jana Novakova", "+420 000 000 000", "jana@in.gr");
        assertTrue(personService.checkDuplicateName("Jana Novakova"));
        assertFalse(personService.checkDuplicateName("Jana Strasna"));
    }
}