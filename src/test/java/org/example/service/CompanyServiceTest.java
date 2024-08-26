package org.example.service;

import org.example.model.Address;
import org.example.model.Company;
import org.example.model.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyServiceTest {

    CompanyService companyService = new CompanyService();
    PersonService personService = new PersonService();
    Person person = new Person();

    @Test
    void checkCompanyValidNameIsFalse() {
        companyService.getCompaniesList().add(0, new Company(1,"Red Hat Czech s.r.o.", new Address("Czech Republic", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(1, new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Kralovo Pole","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(2, new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(3, new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));


        assertFalse(companyService.checkCompanyValidName("Red Hat Czech s.r.o."));

    }

    @Test
    void checkCompanyValidNameIsTrue() {
        companyService.getCompaniesList().add(0, new Company(1,"Red Hat Czech s.r.o.", new Address("Czech Republic", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(1, new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Kralovo Pole","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(2, new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(3, new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));


        assertTrue(companyService.checkCompanyValidName("HP Inc. Czech Republic, s.r.o."));
    }
    @Test
    void idMaxValue() {
        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", new Address("Czech Republic", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Kralovo Pole","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));

        assertEquals(4, companyService.idMaxValue());
    }

    @Test
    void autoIdEditorIs1() {
        companyService.addCompany("Red Hat Czech s.r.o.", "Czech Republic", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111);
        companyService.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        assertEquals(1, companyService.getCompaniesList().stream().filter(cn->cn.getName().equals("Red Hat Czech s.r.o.")).map(id->id.getId()).toList().get(0));
    }

    @Test
    void autoIdEditorIs3() {
        companyService.getCompaniesList().add(new Company(1,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Kralovo Pole","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(new Company(2,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));
        companyService.addCompany("Red Hat Czech s.r.o.", "Czech Republic", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111);
        assertEquals(3, companyService.getCompaniesList().stream().filter(cn->cn.getName().equals("Red Hat Czech s.r.o.")).map(Company::getId).toList().get(0));
    }

    @Test
    void employPerson() {
        companyService.getCompaniesList().add(0, new Company(1,"Red Hat Czech s.r.o.", new Address("Czech Republic", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        personService.addPerson("Jana Novakova", "+420 000 000 001", "jana@ip.gr");
        personService.addPerson("Jana Stara", "+420 000 000 00", "jana@outlook.gr");
        Company companyId01 = companyService.getCompaniesList().stream().filter(c->c.getId() == 1).findAny().get();
        Person personId1 = personService.getPersonList().stream().filter(p->p.getId() == 1).findAny().get();
        companyService.employPerson(companyId01, personId1);
        assertEquals(1, companyId01.getEmployeesList().size());
        assertEquals("Red Hat Czech s.r.o.", personId1.getEmployers().getName());
    }

    @Test
    void findCompany() {
        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", new Address("Czech Republic", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Kralovo Pole","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));


        Company ibm = companyService.getCompaniesList().stream().filter(c->c.getName().equals("IBM Česká republika, spol. s r.o.")).findAny().get();
        Company redhat = companyService.getCompaniesList().stream().filter(c->c.getName().equals("Red Hat Czech s.r.o.")).findAny().get();


        assertEquals(ibm, companyService.findCompaniesByName("Ibm").stream().findFirst().get());
        assertEquals(redhat, companyService.findCompaniesByAddress("Purkynova").stream().findFirst().get());
        assertEquals(ibm, companyService.findCompany(3).stream().findAny().get());
        assertEquals(redhat, companyService.findCompany("purkynova").stream().findAny().get());
    }

    @Test
    void updateCompany() {
        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", new Address("Czech Republic", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Kralovo Pole","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));

    }


    @Test
    void companyEmployees() {
        Company redHat = new Company(1,"Red Hat Czech s.r.o.", new Address("Czech Republic", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111));
        Company atandt = new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Kralovo Pole","Holandská","639 00", 8));

        companyService.getCompaniesList().add(redHat);
        companyService.getCompaniesList().add(atandt);

        Person petr = new Person(1,"Petr Novak", "+420 000 000 121", "pd.novak@ip.gr");
        Person jana = new Person(2,"Jana", "+420 000 123 001", "pe.novak@ip.gr");
        Person novak = new Person(3,"Novak", "+420 123 000 001", "pg.novak@ip.gr");

        personService.getPersonList().add(petr);
        personService.getPersonList().add(jana);
        personService.getPersonList().add(novak);

        personService.getPersonList().add(petr);
        companyService.employPerson(redHat, petr);
        personService.getPersonList().add(jana);
        companyService.employPerson(redHat, jana);
        personService.getPersonList().add(novak);
        companyService.employPerson(atandt, novak);

        List<Person> redHatEmployees = List.of(petr,jana);

        assertEquals(redHatEmployees, companyService.companyEmployees(redHat));

    }

    @Test
    void findCompaniesByAddress() {
        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", new Address("England", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Kralovo Pole","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));


        assertEquals(companyService.selectCompanyById(1), companyService.findCompaniesByAddress("Engl").stream().findFirst().get());

    }

    @Test
    void removeCompany() {

        personService.addPerson("Petr Novak", "+420 000 000 001", "p.novak@ip.gr");
        personService.addPerson("Jana Petrova", "+420 000 000 002", "j.petrova@outlook.gr");
        personService.addPerson("Eva Chuda", "+420 000 000 003", "e.chuda@gmail.com");
        personService.addPerson("Kuba Ondri", "+420 000 000 004", "k.ondri@seznam.gr");
        personService.addPerson("Pepa Pepanek", "+420 000 000 005", "p.pepanek@in.gr");
        personService.addPerson("Iva Koka", "+420 000 000 006", "i.koka@google.gr");
        personService.addPerson("Christos Stefanakis", "+420 912 345 789", "ch@ch.com");

        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", new Address("England", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Kralovo Pole","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));

//        companyService.removeCompany(1);

        personService.getPersonList().forEach(System.out::println);
        companyService.getCompaniesList().forEach(System.out::println);

        personService.removeCompanyFromPerson(companyService.selectCompanyById(1));
        companyService.removeCompany(companyService.selectCompanyById(1));


        assertEquals(3, companyService.getCompaniesList().size());
    }

    @Test
    void findCompaniesByCountry() {
        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", new Address("England", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Kralovo Pole","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));


        assertEquals(companyService.selectCompanyById(1), companyService.findCompaniesByCountry("Engl").stream().findFirst().get());

    }

    @Test
    void findCompaniesByCityName() {
        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", new Address("England", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Kralovo Pole","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Zlin", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));

        assertEquals(companyService.selectCompanyById(3), companyService.findCompaniesByCityName("Zlin").stream().findFirst().get());
    }

    @Test
    void findCompaniesByStreetName() {
        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", new Address("England", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Kralovo Pole","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));

        assertEquals(companyService.selectCompanyById(2), companyService.findCompaniesByStreetName("Holandská").stream().findFirst().get());
    }

    @Test
    void findCompaniesByStateAbbreviation() {
        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", new Address("England", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Styrice","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));

        assertEquals(companyService.selectCompanyById(2), companyService.findCompaniesByStateAbbreviation("Styrice").stream().findFirst().get());
    }

    @Test
    void findCompaniesByBuildNumber() {
        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", new Address("England", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Styrice","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));

        assertEquals(companyService.selectCompanyById(2), companyService.findCompaniesByBuildNumber(8).stream().findFirst().get());
    }

    @Test
    void findCompaniesByZipCode() {
        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", new Address("England", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Styrice","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));

        assertEquals(companyService.selectCompanyById(1), companyService.findCompaniesByZipCode("61200").stream().findFirst().get());
    }

    @Test
    void signUpPerson() {
    }
}