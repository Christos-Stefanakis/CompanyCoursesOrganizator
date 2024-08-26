package org.example;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.example.model.Address;
import org.example.model.Company;
import org.example.service.CompanyService;
import org.example.service.PersonService;
import org.example.view.CompanyView;
import org.example.view.MainView;
import org.example.view.PersonView;

public class Main {
    public static void main(String[] args) {
//        TextIO textIO = TextIoFactory.getTextIO();
//        PersonService personService = new PersonService();
//        CompanyService companyService = new CompanyService();
//        PersonView personView = new PersonView(textIO, personService);
//        CompanyView companyView = new CompanyView(textIO, companyService);
//
//        MainView mainView = new MainView(textIO,personView, companyView);
//        mainView.showMenu();
//        textIO.dispose();

        PersonService personService = new PersonService();
        CompanyService companyService = new CompanyService();

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

        companyService.employPerson(companyService.selectCompanyById(1), personService.selectPersonById(1));

        //companyService.removeCompany(companyService.selectCompanyById(1));
        personService.removeCompanyFromPerson(companyService.selectCompanyById(1));

        companyService.getCompaniesList().forEach(System.out::println);
        personService.getPersonList().forEach(System.out::println);

//        companyService.companyEmployees(companyService.selectCompanyById(1)).forEach(System.out::println);





        //companyService.removeCompany(1);
    }
}