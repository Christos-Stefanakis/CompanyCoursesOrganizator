package org.example.service;

import org.example.model.Company;
import org.example.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyServiceTest {

    CompanyService companyService = new CompanyService();
    PersonService personService = new PersonService();
    Person person = new Person();

    @Test
    void checkCompanyValidNameIsFalse() {
        companyService.getCompaniesList().add(0, new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        companyService.getCompaniesList().add(1, new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        companyService.getCompaniesList().add(2, new Company(3,"IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic"));
        companyService.getCompaniesList().add(3, new Company(4,"Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));


        assertFalse(companyService.checkCompanyValidName("Red Hat Czech s.r.o."));

    }

    @Test
    void checkCompanyValidNameIsTrue() {
        companyService.getCompaniesList().add(0, new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        companyService.getCompaniesList().add(1, new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        companyService.getCompaniesList().add(2, new Company(3,"IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic"));
        companyService.getCompaniesList().add(3, new Company(4,"Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));


        assertTrue(companyService.checkCompanyValidName("HP Inc. Czech Republic, s.r.o."));
    }
    @Test
    void idMaxValue() {
        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        companyService.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        companyService.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic"));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));
        assertEquals(4, companyService.idMaxValue());
    }

    @Test
    void autoIdEditorIs1() {
        companyService.addCompany("Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic");

        assertEquals(1, companyService.getCompaniesList().stream().filter(cn->cn.getName().equals("Red Hat Czech s.r.o.")).map(id->id.getId()).toList().get(0));
    }

    @Test
    void autoIdEditorIs3() {
        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        companyService.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        companyService.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));
        companyService.addCompany("IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic");
        assertEquals(3, companyService.getCompaniesList().stream().filter(cn->cn.getName().equals("IBM Česká republika, spol. s r.o.")).map(Company::getId).toList().get(0));
    }

    @Test
    void employPerson() {
        companyService.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
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
        companyService.getCompaniesList().add(0, new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        companyService.getCompaniesList().add(1, new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        companyService.getCompaniesList().add(2, new Company(3,"IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic"));
        companyService.getCompaniesList().add(3, new Company(4,"Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));

        Company ibm = companyService.getCompaniesList().stream().filter(c->c.getName().equals("IBM Česká republika, spol. s r.o.")).findAny().get();
        Company redhat = companyService.getCompaniesList().stream().filter(c->c.getName().equals("Red Hat Czech s.r.o.")).findAny().get();

        assertEquals(ibm, companyService.findCompany("ibm").stream().findAny().get());
        assertEquals(ibm, companyService.findCompany(3).stream().findAny().get());
        assertEquals(redhat, companyService.findCompany("purkynova").stream().findAny().get());
    }

    @Test
    void updateCompany() {
        companyService.getCompaniesList().add(0, new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        companyService.getCompaniesList().add(1, new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        companyService.getCompaniesList().add(2, new Company(3,"IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic"));
        companyService.getCompaniesList().add(3, new Company(4,"Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));
    }


    @Test
    void companyEmployees() {
        Company redHat = new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic");
        Company atandt = new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic");

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

}