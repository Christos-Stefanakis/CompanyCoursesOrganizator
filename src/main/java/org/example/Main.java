package org.example;

import org.example.model.Company;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
//        company.addCompany("Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic");
        company.getCompaniesList().forEach(System.out::println);
        company.getCompaniesList().add(new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic"));
        company.getCompaniesList().add(new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic"));
        company.getCompaniesList().add(new Company(4,"Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic"));

        company.getCompaniesList().forEach(System.out::println);

        company.addCompany("IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic");

        company.getCompaniesList().forEach(System.out::println);

        int id = company.getCompaniesList().stream().filter(cn->cn.getName().equals("IBM Česká republika, spol. s r.o.")).map(Company::getId).toList().get(0);

        System.out.println(id);
    }
}