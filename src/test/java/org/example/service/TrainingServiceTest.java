package org.example.service;

import org.example.model.Address;
import org.example.model.Company;
import org.example.model.Training;
import org.example.model.TrainingType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainingServiceTest {

    PersonService personService = new PersonService();
    CompanyService companyService = new CompanyService();
    TrainingService trainingService =new TrainingService();
    TrainingTypeService trainingTypeService = new TrainingTypeService();

    @Test
    void updateTrainingTime() {
        TrainingService trainingService =new TrainingService();
        TrainingTypeService trainingTypeService = new TrainingTypeService();

        trainingTypeService.addTrainingType("AA01", "CompanyR", "lallallalal allala");
        trainingTypeService.addTrainingType("BB02", "OmadaR", "be bebebebebb bebe");
        trainingTypeService.addTrainingType("CC02", "TeamR", "Something");

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(1), trainingService.addTime(2024,10,5,9,30), "Brno", 10);
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(2), trainingService.addTime(2024,9,4,10,0), "Prague",10);
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(3), trainingService.addTime(2024,10,5,11,30), "Brno",10);

        Training expectList01 =  new Training(1, trainingService.addTime(2004,10,5,9,30), "Brno",10,trainingTypeService.getTrainingTypeById(1), new ArrayList<>());
        assertEquals(expectList01.getLocation(), trainingService.getTrainingScheduleById(1).getLocation());
        trainingService.updateTrainingTime(1, trainingTypeService.getTrainingTypeById(1), trainingService.addTime(2004,10,5,9,0), "Prague");
        Training expectList02 =  new Training(6, trainingService.addTime(2004,10,5,9,30), "Prague",10,trainingTypeService.getTrainingTypeById(1), new ArrayList<>());
        assertEquals(expectList02.getLocation(), trainingService.getTrainingScheduleById(1).getLocation());
    }

    @Test
    void removeTrainingTime() {
        TrainingService trainingService =new TrainingService();
        TrainingTypeService trainingTypeService = new TrainingTypeService();

        trainingTypeService.addTrainingType("AA01", "CompanyR", "lallallalal allala");
        trainingTypeService.addTrainingType("BB02", "OmadaR", "be bebebebebb bebe");
        trainingTypeService.addTrainingType("CC02", "TeamR", "Something");

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(1), trainingService.addTime(2024,10,5,9,30), "Brno",10);
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(2), trainingService.addTime(2024,9,4,10,0), "Prague",10);
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(3), trainingService.addTime(2024,10,5,11,30), "Brno",10);
        assertEquals(3, trainingService.getTrainingSchedule().size());
        trainingService.removeTrainingTime(2);
        assertEquals(2,  trainingService.getTrainingSchedule().size());
    }

    @Test
    void getTrainingScheduleById() {
        TrainingService trainingService =new TrainingService();
        TrainingTypeService trainingTypeService = new TrainingTypeService();

        trainingTypeService.addTrainingType("AA01", "CompanyR", "lallallalal allala");
        trainingTypeService.addTrainingType("BB02", "OmadaR", "be bebebebebb bebe");
        trainingTypeService.addTrainingType("CC02", "TeamR", "Something");

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(1), trainingService.addTime(2024,10,5,9,30), "Brno",10);
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(2), trainingService.addTime(2024,9,4,10,0), "Prague",10);
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(3), trainingService.addTime(2024,10,5,11,30), "Brno",10);

        Training expected = trainingService.getTrainingSchedule().stream().filter(t->t.getId() == 2).findFirst().get();

        assertEquals(expected, trainingService.getTrainingScheduleById(2));
    }

    @Test
    void findTrainingScheduleByDate() {

    }

    @Test
    void findTrainingScheduleByLocation() {
        TrainingService trainingService =new TrainingService();
        TrainingTypeService trainingTypeService = new TrainingTypeService();

        trainingTypeService.addTrainingType("AA01", "CompanyR", "lallallalal allala");
        trainingTypeService.addTrainingType("BB02", "OmadaR", "be bebebebebb bebe");
        trainingTypeService.addTrainingType("CC02", "TeamR", "Something");

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(1), trainingService.addTime(2024,10,5,9,30), "Brno",10);
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(2), trainingService.addTime(2024,9,4,10,0), "Prague",10);
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(3), trainingService.addTime(2024,10,5,11,30), "Brno",10);

        Training expected = trainingService.getTrainingSchedule().stream().filter(t->t.getId() == 2).findFirst().get();
        assertEquals(expected, trainingService.findTrainingScheduleByLocation("Prague").stream().findFirst().get());
    }

    @Test
    void findTrainingScheduleByTrainingType() {
        TrainingService trainingService =new TrainingService();
        TrainingTypeService trainingTypeService = new TrainingTypeService();

        TrainingType AA01 =new TrainingType(1, "AA01", "CompanyR", "lallallalal allala");
        trainingTypeService.addTrainingType("BB02", "OmadaR", "be bebebebebb bebe");
        trainingTypeService.addTrainingType("CC02", "TeamR", "Something");

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(2), trainingService.addTime(2024,10,5,9,30), "Brno",10);
        Training time02 = new Training(2,trainingService.addTime(2024,9,4,10,0),"Prague",10,AA01, new ArrayList<>());
        trainingService.getTrainingSchedule().add(time02);
        //trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(3), trainingService.addTime(2024,10,5,11,30), "Brno");


        assertEquals(time02, trainingService.findTrainingScheduleByTrainingType(AA01).stream().findFirst().get());
    }

    @Test
    void findScheduleTime() {
        TrainingService trainingService =new TrainingService();
        TrainingTypeService trainingTypeService = new TrainingTypeService();

        TrainingType AA01 =new TrainingType(1, "AA01", "CompanyR", "lallallalal allala");
        trainingTypeService.addTrainingType("BB02", "OmadaR", "be bebebebebb bebe");
        trainingTypeService.addTrainingType("CC02", "TeamR", "Something");

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(2), trainingService.addTime(2024,10,5,9,30), "Brno",10);
        Training time02 = new Training(2,trainingService.addTime(2024,9,4,10,0),"Prague",10,AA01, new ArrayList<>());
        trainingService.getTrainingSchedule().add(time02);

        assertEquals(time02, trainingService.findScheduleTime("Pra").stream().findFirst().get());
    }

    @Test
    void addTime() {
        TrainingService trainingService =new TrainingService();
        Date expected = new Date (2024,1,1,10,10);
        assertEquals(expected, trainingService.addTime(2024,1,1,10,10));
    }

    @Test
    void singUpPerson() {

        personService.addPerson("Petr Novak", "+420 000 000 001", "p.novak@ip.gr");
        personService.addPerson("Jana Petrova", "+420 000 000 002", "j.petrova@outlook.gr");
        personService.addPerson("Eva Chuda", "+420 000 000 003", "e.chuda@gmail.com");
        personService.addPerson("Kuba Ondri", "+420 000 000 004", "k.ondri@seznam.gr");
        personService.addPerson("Pepa Pepanek", "+420 000 000 005", "p.pepanek@in.gr");
        personService.addPerson("Iva Koka", "+420 000 000 006", "i.koka@google.gr");

        companyService.getCompaniesList().add(0, new Company(1,"Red Hat Czech s.r.o.", new Address("Czech Republic", "Brno", "Kralovo Pole", "Purkynova", "612 00", 111)));
        companyService.getCompaniesList().add(1, new Company(2,"AT&T Global Network Services Czech Republic s.r.o.", new Address("Czech Republic", "Brno","Kralovo Pole","Holandská","639 00", 8)));
        companyService.getCompaniesList().add(2, new Company(3,"IBM Česká republika, spol. s r.o.", new Address("Czech Republic", "Prague", "Prague 4", "V Parku", "148 00", 4)));
        companyService.getCompaniesList().add(3, new Company(4,"Avast Software s.r.o.", new Address("Czech Republic", "Prague", "Prague 4", "Pikrtova", "140 00", 1)));

        trainingTypeService.addTrainingType("AA01", "CompanyR", "lallallalal allala");
        trainingTypeService.addTrainingType("BB02", "OmadaR", "be bebebebebb bebe");
        trainingTypeService.addTrainingType("CC02", "TeamR", "Something");

        trainingService.getSignUpPersons().put(0, List.of(personService.selectPersonById(1)));

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(2), trainingService.addTime(2024,10,5,9,30), "Brno",10);

        companyService.signUpPerson(trainingService.getTrainingScheduleById(1), personService.selectPersonById(1));

        trainingService.getTrainingScheduleById(1).getPersons().forEach(System.out::println);
    }
}