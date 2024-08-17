package org.example;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        company.getCompaniesList().add(0, new Company(0, "Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        company.getCompaniesList().add(1, new Company(1,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        company.getCompaniesList().add(2, new Company(2,"IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic"));
        company.getCompaniesList().add(3, new Company(3,"Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));

        company.getCompaniesList().stream().map(e->e.getId()).forEach(System.out::println);
    }
}