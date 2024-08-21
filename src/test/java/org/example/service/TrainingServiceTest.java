package org.example.service;

import org.example.model.Training;
import org.example.model.TrainingType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainingServiceTest {


    @Test
    void updateTrainingTime() {
        TrainingService trainingService =new TrainingService();
        TrainingTypeService trainingTypeService = new TrainingTypeService();

        trainingTypeService.addTrainingType("AA01", "CompanyR", "lallallalal allala");
        trainingTypeService.addTrainingType("BB02", "OmadaR", "be bebebebebb bebe");
        trainingTypeService.addTrainingType("CC02", "TeamR", "Something");

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(1), trainingService.addTime(2024,10,5,9,30), "Brno");
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(2), trainingService.addTime(2024,9,4,10,0), "Prague");
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(3), trainingService.addTime(2024,10,5,11,30), "Brno");

        Training expectList01 =  new Training(1, trainingService.addTime(2004,10,5,9,30), "Brno",trainingTypeService.getTrainingTypeById(1));
        assertEquals(expectList01.getLocation(), trainingService.getTrainingScheduleById(1).getLocation());
        trainingService.updateTrainingTime(1, trainingTypeService.getTrainingTypeById(1), trainingService.addTime(2004,10,5,9,0), "Prague");
        Training expectList02 =  new Training(6, trainingService.addTime(2004,10,5,9,30), "Prague",trainingTypeService.getTrainingTypeById(1));
        assertEquals(expectList02.getLocation(), trainingService.getTrainingScheduleById(1).getLocation());
    }

    @Test
    void removeTrainingTime() {
        TrainingService trainingService =new TrainingService();
        TrainingTypeService trainingTypeService = new TrainingTypeService();

        trainingTypeService.addTrainingType("AA01", "CompanyR", "lallallalal allala");
        trainingTypeService.addTrainingType("BB02", "OmadaR", "be bebebebebb bebe");
        trainingTypeService.addTrainingType("CC02", "TeamR", "Something");

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(1), trainingService.addTime(2024,10,5,9,30), "Brno");
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(2), trainingService.addTime(2024,9,4,10,0), "Prague");
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(3), trainingService.addTime(2024,10,5,11,30), "Brno");
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

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(1), trainingService.addTime(2024,10,5,9,30), "Brno");
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(2), trainingService.addTime(2024,9,4,10,0), "Prague");
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(3), trainingService.addTime(2024,10,5,11,30), "Brno");

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

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(1), trainingService.addTime(2024,10,5,9,30), "Brno");
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(2), trainingService.addTime(2024,9,4,10,0), "Prague");
        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(3), trainingService.addTime(2024,10,5,11,30), "Brno");

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

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(2), trainingService.addTime(2024,10,5,9,30), "Brno");
        Training time02 = new Training(2,trainingService.addTime(2024,9,4,10,0),"Prague",AA01);
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

        trainingService.addTrainingTime(trainingTypeService.getTrainingTypeById(2), trainingService.addTime(2024,10,5,9,30), "Brno");
        Training time02 = new Training(2,trainingService.addTime(2024,9,4,10,0),"Prague",AA01);
        trainingService.getTrainingSchedule().add(time02);

        assertEquals(time02, trainingService.findScheduleTime("Pra").stream().findFirst().get());
    }

    @Test
    void addTime() {
        TrainingService trainingService =new TrainingService();
        Date expected = new Date (2024,1,1,10,10);
        assertEquals(expected, trainingService.addTime(2024,1,1,10,10));
    }
}