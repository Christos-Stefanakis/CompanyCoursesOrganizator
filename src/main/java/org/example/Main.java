package org.example;

import org.example.model.Company;
import org.example.model.Person;
import org.example.model.TrainingType;
import org.example.service.CompanyService;
import org.example.service.PersonService;
import org.example.service.TrainingService;
import org.example.service.TrainingTypeService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonService personService = new PersonService();
        CompanyService companyService = new CompanyService();


        Person person01 = new Person(1,"Jana A", "+420 000 000 00", "jana@outlook.gr");
        //Person person02 = new Person(2,"Jana B", "+420 000 000 002", "janain.gr");
        Person person03 = new Person(3,"Jana C", "+420 000 000 003", "jana@seznam.gr");
        Person person04 = new Person(4,"Jana D", "+420 000 000 004", "jana@in.gr");
        Person person05 = new Person(5,"Jana E", "+420 000 000 001", "jana@google.gr");

        Company company1 = new Company(1,"Red Hat Czech s.r.o.", "Purkynova 111, 612 00 Brno, Czech Republic");
        Company company2 = new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", "Holandská 6/8, 639 00 Brno, Czech Republic");
        Company company3 = new Company(3,"IBM Česká republika, spol. s r.o.", "V Parku 2294/4, 148 00 Prague 4, Czech Republic");
        Company company4 = new Company(4,"Avast Software s.r.o.", "Pikrtova 1737/1A, 140 00 Prague 4, Czech Republic");

        companyService.getCompaniesList().add(company1);
        companyService.getCompaniesList().add(company2);
        companyService.getCompaniesList().add(company3);
        companyService.getCompaniesList().add(company4);
        personService.getPersonList().add(person01);

        personService.getPersonList().add(person03);
        personService.getPersonList().add(person04);
        personService.getPersonList().add(person05);
        personService.getPersonList().add(new Person(2,"Jana B", "+420 000 000 002", "janain.gr"));

        person05.setEmployers(company1);
        person04.setEmployers(company1);
        company1.getEmployeesList().add(person05);
        company1.getEmployeesList().add(person04);

        companyService.employPerson(company2, person01);
        companyService.employPerson(company2, person03);

        Company curentCompany = companyService.selectCompanyById(2);
       // personService.getPersonList().forEach(System.out::println);
        companyService.companyEmployees(curentCompany).forEach(System.out::println);

        TrainingService trainingService =new TrainingService();
        TrainingTypeService trainingTypeService = new TrainingTypeService();

        trainingTypeService.getTrainingTypeList().add(new TrainingType(1, "CC02", "Kaa","Something"));
        trainingTypeService.addTrainingType("AA01", "CompanyR", "lallallalal allala");
        trainingTypeService.addTrainingType("BB02", "OmadaR", "be bebebebebb bebe");
        trainingTypeService.addTrainingType("CC02", "TeamR", "Something");

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(1), trainingService.addTime(2024,10,5,9,30), "Brno");
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(1), trainingService.addTime(2024,9,4,10,0), "Prague");
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(1), trainingService.addTime(2024,10,5,11,30), "Brno");

        trainingTypeService.getTrainingTypeList().forEach(System.out::println);
        trainingService.getTrainingSchedule().forEach(System.out::println);
    }

    }
