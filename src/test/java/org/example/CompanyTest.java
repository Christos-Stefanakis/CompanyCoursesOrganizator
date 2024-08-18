package org.example;

import org.example.model.Company;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CompanyTest {

    Company companyTest = new Company();



    @Test
    void checkCompanyValidNameIsFalse() {
        companyTest.getCompaniesList().add(0, new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        companyTest.getCompaniesList().add(1, new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        companyTest.getCompaniesList().add(2, new Company(3,"IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic"));
        companyTest.getCompaniesList().add(3, new Company(4,"Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));


        assertFalse(companyTest.checkCompanyValidName("Red Hat Czech s.r.o."));

    }

    @Test
    void checkCompanyValidNameIsTrue() {
        companyTest.getCompaniesList().add(0, new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        companyTest.getCompaniesList().add(1, new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        companyTest.getCompaniesList().add(2, new Company(3,"IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic"));
        companyTest.getCompaniesList().add(3, new Company(4,"Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));


        assertTrue(companyTest.checkCompanyValidName("HP Inc. Czech Republic, s.r.o."));
    }
    @Test
    void idMaxValue() {
        companyTest.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        companyTest.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        companyTest.getCompaniesList().add(new Company(3,"IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic"));
        companyTest.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));
        assertEquals(4, companyTest.idMaxValue());
    }

    @Test
    void autoIdEditorIs1() {
        companyTest.addCompany("Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic");

        assertEquals(1, companyTest.getCompaniesList().stream().filter(cn->cn.getName().equals("Red Hat Czech s.r.o.")).map(id->id.getId()).toList().get(0));
    }

    @Test
    void autoIdEditorIs3() {
        companyTest.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        companyTest.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        companyTest.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));
        companyTest.addCompany("IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic");
        assertEquals(3, companyTest.getCompaniesList().stream().filter(cn->cn.getName().equals("IBM Česká republika, spol. s r.o.")).map(Company::getId).toList().get(0));
    }

}