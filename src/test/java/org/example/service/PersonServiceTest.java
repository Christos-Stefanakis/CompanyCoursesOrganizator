package org.example.service;

import org.example.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

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

    @Test
    void personsListSize() {
        assertEquals(0, personService.personsListSize());
        personService.addPerson("Jana Novakova", "+420 000 000 000", "jana@in.gr");
        assertEquals(1, personService.personsListSize());
    }

    @Test
    void testPersonID() {
        personService.addPerson("Jana Novakova", "+420 000 000 000", "jana@in.gr");
        assertEquals(1, personService.getPersonList().stream().map(p->p.getId()).toList().get(0));
    }

    @Test
    void testPersonStuff(){
        personService.addPerson("Jana Novakova", "+420 000 000 000", "jana@in.gr");
        assertEquals("Úřad práce", personService.getPersonList().stream().map(p->p.getEmployers().getName()).toList().get(0));
    }

    @Test
    void findPerson() {
        personService.addPerson("Petr Novak", "+420 000 000 001", "p.novak@ip.gr");
        personService.addPerson("Jana Petrova", "+420 000 000 002", "j.petrova@outlook.gr");
        personService.addPerson("Eva Chuda", "+420 000 000 003", "e.chuda@gmail.com");
        personService.addPerson("Kuba Ondri", "+420 000 000 004", "k.ondri@seznam.gr");
        personService.addPerson("Pepa Pepanek", "+420 000 000 005", "p.pepanek@in.gr");
        personService.addPerson("Iva Koka", "+420 000 000 006", "i.koka@google.gr");

        Person evaChuda = personService.getPersonList().stream().filter(p->p.getName().equals("Eva Chuda")).findAny().get();
        Person kubaOndri = personService.getPersonList().stream().filter(p->p.getName().equals("Kuba Ondri")).findAny().get();

        assertEquals(evaChuda, personService.findPerson("+420000000003").stream().findAny().get());
        assertEquals(evaChuda, personService.findPerson(3).stream().findAny().get());
        assertEquals(evaChuda, personService.findPerson("e.chuda@gmail.com").stream().findAny().get());
        assertEquals(kubaOndri, personService.findPerson("Kuba Ondri").stream().findAny().get());
    }

    @Test
    void updatePeron() {
        personService.addPerson("Petr Novak", "+420 000 000 001", "p.novak@ip.gr");
        personService.addPerson("Jana Petrova", "+420 000 000 002", "j.petrova@outlook.gr");
        personService.addPerson("Eva Chuda", "+420 000 000 003", "e.chuda@gmail.com");
        personService.addPerson("Kuba Ondri", "+420 000 000 004", "k.ondri@seznam.gr");
        personService.addPerson("Pepa Pepanek", "+420 000 000 005", "p.pepanek@in.gr");
        personService.addPerson("Iva Koka", "+420 000 000 006", "i.koka@google.gr");
        Person evaChuda = personService.getPersonList().stream().filter(p->p.getName().equals("Eva Chuda")).findAny().get();
        personService.updatePeron(3,"Eva Chudakova", "+420356486123","eva.chudakova@gmail.com");
        assertEquals("Eva Chudakova", evaChuda.getName());
        assertEquals("+420356486123", evaChuda.getPhoneNumber());
        assertEquals("eva.chudakova@gmail.com", evaChuda.getEmailAddress());

    }
}