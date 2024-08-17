package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person person = new Person();

    @Test
    void nameValedFlase() {
        String[] validNames = {"John Doe","Alice Smith","Jean-Luc Picard"};
        String[] invalidNames = {"John", "john doe", "John-Doe", "John  Doe"};
        for(String n: invalidNames){
            assertFalse(person.nameValid(n));
        }
        for(String n: validNames){
            assertTrue(person.nameValid(n));
        }

    }

    @Test
    void phoneValid() {
        String[] validPhoneNumbers = {"+420 000 000 000", "+420000000000", "+300 000000000"};
        String[] invalidPhoneNumbers = {"000 000 000", "658 657 1"};
        for(String n: validPhoneNumbers){
            assertTrue(person.phoneValid(n));
        }
        for (String n: invalidPhoneNumbers){
            assertFalse(person.phoneValid(n));
        }
    }

    @Test
    void emailValidator() {
        String[] validMails = {"jan@gmail.com", "jan.nov@seznam.cz"};
        String[] invalidMails = {"jan.gmail.com", "jan@com"};
        for(String m: validMails){
            assertTrue(person.emailValidator(m));
        }
        for(String m:invalidMails){
            assertFalse(person.emailValidator(m));
        }
    }

    @Test
    void addPerson() {
        person.addPerson("Jana Novakova", "+420 000 000 001", "jana@ip.gr");
        person.addPerson("Jana Stara", "+420 000 000 00", "jana@outlook.gr");
        person.addPerson("Jana Chuda", "+420 000 000 002", "janain.gr");
        person.addPerson("Jana Novakova", "+420 000 000 003", "jana@seznam.gr");
        person.addPerson("Jana Strasna", "+420 000 000 004", "jana@in.gr");
        person.addPerson("Jana Strasna", "+420 000 000 001", "jana@google.gr");
        assertEquals(2, person.getPersonList().size());
    }

    @Test
    void checkDuplicatePhone() {
        person.addPerson("Jana Novakova", "+420 000 000 000", "jana@in.gr");
        assertTrue(person.checkDuplicatePhone("+420000000000"));
        assertTrue(person.checkDuplicatePhone("+420 000 000 000"));
        assertFalse(person.checkDuplicatePhone("+420000000001"));
    }

    @Test
    void checkDuplicateEmail() {
        person.addPerson("Jana Novakova", "+420 000 000 000", "jana@in.gr");
        assertTrue(person.checkDuplicateEmail("jana@in.gr"));
        assertFalse(person.checkDuplicateEmail("jana@gmail.gr"));
    }

    @Test
    void checkDuplicateName() {
        person.addPerson("Jana Novakova", "+420 000 000 000", "jana@in.gr");
        assertTrue(person.checkDuplicateName("Jana Novakova"));
        assertFalse(person.checkDuplicateName("Jana Strasna"));
    }
}