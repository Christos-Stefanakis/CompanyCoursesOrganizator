package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CompanyTest {

    Company company = new Company();



    @Test
    void checkCompanyValidNameIsFalse() {
        company.getCompaniesList().add(0, new Company(1, "Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        company.getCompaniesList().add(1, new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        company.getCompaniesList().add(2, new Company(3, "IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic"));
        company.getCompaniesList().add(3, new Company(4, "Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));


        assertFalse(company.checkCompanyValidName("Red Hat Czech s.r.o."));

    }

    @Test
    void checkCompanyValidNameIsTrue() {
        company.getCompaniesList().add(0, new Company(1, "Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        company.getCompaniesList().add(1, new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        company.getCompaniesList().add(2, new Company(3, "IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic"));
        company.getCompaniesList().add(3, new Company(4, "Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));


        assertTrue(company.checkCompanyValidName("HP Inc. Czech Republic, s.r.o."));
    }

    @Test
    void idMaxValue() {
        company.getCompaniesList().add(0, new Company(1, "Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        company.getCompaniesList().add(1, new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        company.getCompaniesList().add(2, new Company(3, "IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic"));
        company.getCompaniesList().add(3, new Company(4, "Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));
        assertEquals(4, company.idMaxValue());
    }


}